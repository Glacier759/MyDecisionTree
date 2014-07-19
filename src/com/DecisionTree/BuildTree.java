
package com.DecisionTree;

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
		for ( String SituName:objAttr.Situation_Count.keySet() ) {
			Situation objSitu = objAttr.SituationMap.get(SituName);
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
		System.out.println("Attribute = " + root.AttributeName);
		System.out.println("Situation = " + root.SituationName);
		System.out.println("Parent = " + root.ParentName);
		System.out.println("is Leaf = " + root.isLeafNode);
		System.out.println( "ChildCount = " + root.ChildCount);
		if ( root.ChildNodes == null  ) {
			return ;
		}
		for ( String SituName:root.ChildNodes.keySet() ) {
			System.out.println("------------------------------------");
			show( root.ChildNodes.get(SituName) );
		}
	}
}