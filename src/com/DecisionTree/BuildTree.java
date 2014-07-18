
package com.DecisionTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.DecisionTree.TextProcessing.Attribute;
import com.DecisionTree.TextProcessing.Situation;

public class BuildTree {
	
	class TreeNode{
		public boolean isLeafNode;
		public String AttributeName;
		public String SituationName;
		public String ParentName;
		public Integer ChildCount;
		public HashMap<String,Boolean> SituationMap = new HashMap<String, Boolean>();
		public HashMap<String, TreeNode> ChildNodes = new HashMap<String, TreeNode>();
		
		public TreeNode() {
			this.ChildCount = 0;
		}
	}
	
	public TreeNode creat( TextProcessing TextPro, String Situ, String ParName ) {
		TreeNode root = new TreeNode();
		InformationGain infoGain = new InformationGain();
		root.AttributeName = infoGain.getMaxGain(TextPro);
		root.SituationName = Situ;
		root.ParentName = ParName;
		root.isLeafNode = false;
		
		Attribute objAttr = null;
		for ( int i = 0; i < TextPro.objAttributeList.size(); i ++ ) {
			if ( TextPro.objAttributeList.get(i).AttributeName.equals(root.AttributeName) ) {
				objAttr = TextPro.objAttributeList.get(i);
				break;
			}
		}
		root.ChildCount = objAttr.SituationMap.size();
		//System.out.println("Attribute Name = " + root.AttributeName);
		for ( String SituName:objAttr.Situation_Count.keySet() ) {
			Situation objSitu = objAttr.SituationMap.get(SituName);
			//System.out.println("SituName = " + SituName + "\t" + objSitu.Result_Count.size());
			if ( objSitu.Result_Count.size() == 1 ) {
				TreeNode leaf = new TreeNode();
				leaf.isLeafNode = true;
				leaf.AttributeName = findResult(SituName, root.AttributeName, TextPro);
				leaf.SituationName = SituName;
				leaf.ParentName = root.AttributeName;
				leaf.ChildCount = 0;
				leaf.ChildNodes = null;
				root.ChildNodes.put(SituName, leaf);
			}
			else {
				List<String> nextTextList = TextPro.getChildTable(root.AttributeName, objSitu.SituationName);
				TextProcessing nextTextPro = new TextProcessing();
				nextTextPro.readText(nextTextList);
				TreeNode ChildNode = creat(nextTextPro, objSitu.SituationName, root.AttributeName);
				root.ChildNodes.put(SituName, ChildNode);
			}
		}
		return root;
	}
	
	public String findResult(String SituName, String ParName, TextProcessing TextPro) {
		
		int resultIndex = 0;
		String result = null;
		for ( int i = 0; i < TextPro.Headers.length; i ++ ) {
			if ( TextPro.Headers[i].equals(ParName) ) {
				resultIndex = i;
			}
		}
		for ( String textLine:TextPro.textList ) {
			String textArray[] = textLine.split(",");
			if ( textArray[resultIndex].equals(SituName) ) {
				result = textArray[textArray.length-1];
				break;
			}
		}
		return result;
	}
	
	public void show( TreeNode root ) {
		//if ( root == null ) {
		//	return;
		//}
		System.out.println("Attribute = " + root.AttributeName);
		System.out.println("Situation = " + root.SituationName);
		System.out.println("Parent = " + root.ParentName);
		System.out.println("is Leaf = " + root.isLeafNode);
		System.out.println( "ChildCount = " + root.ChildCount);
		if ( root.ChildNodes == null  ) {
			return ;
		}
		for ( String SituName:root.ChildNodes.keySet() ) {
			//System.out.println("SituName = " + SituName);
			System.out.println("------------------------------------");
			show( root.ChildNodes.get(SituName) );
		}
	}
}

/*
public class BuildTree {
	
	class TreeNode{
		public boolean isLeafNode;
		public String AttributeName;
		public Integer ChildCount;
		public HashMap<String,Boolean> SituationMap = new HashMap<String, Boolean>();
		public HashMap<String, TreeNode> ChildNodes = new HashMap<String, TreeNode>();
		
		public TreeNode(String AttributeName) {
			this.AttributeName = AttributeName;
			this.ChildCount = 0;
		}
	}

	public ArrayList<AttributeClass> ResultList;
	public HashMap<String, Double> AttrGainRatioMap;
	public BuildTree( HashMap<String, Double> AttrGainRatioMap, ArrayList<AttributeClass> ResultList ) {
		this.ResultList = ResultList;
		this.AttrGainRatioMap = AttrGainRatioMap;
	}
	
	public void getDecisionTree() {
		
		TreeNode root = getNode(getMaxGainAttr(AttrGainRatioMap));
		
		showTree(root);
	}
	
	public void showTree( TreeNode root ) {
		System.out.println("-----------"+root.AttributeName+"------------");
		System.out.println("节点名称 = " + root.AttributeName);
		System.out.println("是否是叶子节点 = " + root.isLeafNode);
		if ( !root.isLeafNode ) {
			System.out.println("所包含的子节点个数 = " + root.ChildNodes.size());
			for ( String name:root.ChildNodes.keySet() ) {
				System.out.println("子节点名称 = " + root.ChildNodes.get(name).AttributeName +"\t对应条件"+name);
			}
			for ( String name : root.SituationMap.keySet() ) {
				System.out.println("记录名 = " + name +"\t指向知否为叶子节点 = " + root.SituationMap.get(name));
				if ( !root.SituationMap.get(name) ) {
					System.out.println(root.ChildNodes.get(name));
				}
			}
			for ( String name:root.ChildNodes.keySet()) {
				showTree(root.ChildNodes.get(name));
			}
		}
		
	}
	
	public TreeNode getNode(String AttrName) {
		if ( AttrGainRatioMap.size() <= 0 ) {	return null;	}
		TreeNode node = new TreeNode(AttrName);
		node.isLeafNode = false;
		AttributeClass objAttr = null;
		for ( AttributeClass tempAttr:ResultList ) {
			if ( tempAttr.AttributeName == node.AttributeName ) {
				objAttr = tempAttr;
				break;
			}
		}
		node.ChildCount = objAttr.SituationMap.size();
		int leafCount = 0;
		for ( String SituName:objAttr.SituationMap.keySet() ) {
			SituationClass objSitu = objAttr.SituationMap.get(SituName);
			if ( objSitu.SituationType.size() == 1 ) {
				for ( String result:objSitu.SituationType.keySet() ) {
					TreeNode leaf = new TreeNode(result);
					leaf.isLeafNode = true;
					leaf.ChildNodes = null;
					leaf.ChildCount = 0;
					node.ChildNodes.put(SituName, leaf);
				}
				leafCount ++;
			}
			else {
				continue;
			}
		}
		ArrayList<String> tempAttrName = new ArrayList<String>();
		for ( int i = 0; i < (node.ChildCount-leafCount); i ++ ) {
			tempAttrName.add(getMaxGainAttr(AttrGainRatioMap));
		}
		for ( String SituName:objAttr.SituationMap.keySet() ) {
			SituationClass objSitu = objAttr.SituationMap.get(SituName);
			if ( objSitu.SituationType.size() != 1 ) {
				TreeNode nextNode = getNode(tempAttrName.remove(0));
			}
		}
		
		return node;
	}
	
	public String getMaxGainAttr( HashMap<String, Double> AttrGainRatioMap ) {
		Double MaxGain = null;
		String MaxGainAttrName = null;
		for ( String AttrName:AttrGainRatioMap.keySet() ) {
			if ( MaxGain == null ) {
				MaxGain = AttrGainRatioMap.get(AttrName);
				MaxGainAttrName = AttrName;
			}
			else {
				if ( MaxGain < AttrGainRatioMap.get(AttrName) ) {
					MaxGain = AttrGainRatioMap.get(AttrName);
					MaxGainAttrName = AttrName;
				}
			}
		}
		this.AttrGainRatioMap.remove(MaxGainAttrName);
		return MaxGainAttrName;
	}
	
}
*/