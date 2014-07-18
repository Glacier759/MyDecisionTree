
package com.DecisionTree;

import java.util.ArrayList;
import java.util.HashMap;

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