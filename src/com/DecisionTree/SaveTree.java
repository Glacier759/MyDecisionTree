
package com.DecisionTree;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.DecisionTree.BuildTree.TreeNode;

public class SaveTree {
	
	Document xmlDoc;
	Element root;
	
	public void save( TreeNode treeRoot ) throws Exception {
		xmlDoc = DocumentHelper.createDocument();
		root = xmlDoc.addElement("root");
		Element nextNode = root.addElement("Decision");
		toXML( treeRoot, nextNode );
		System.out.println(xmlDoc.asXML());
		FileUtils.writeStringToFile(new File("output.xml"), xmlDoc.asXML());
	}
	
	public void toXML( TreeNode treeRoot, Element node ) {
		
		if ( treeRoot.ChildNodes == null  ) {
			return ;
		}
		for ( String SituName:treeRoot.ChildNodes.keySet() ) {
			Element nextNode = node.addElement(treeRoot.AttributeName);
			TreeNode childNode = treeRoot.ChildNodes.get(SituName);
			nextNode.addAttribute("value", childNode.SituationName);
			if ( childNode.isLeafNode ) {
				nextNode.setText(childNode.AttributeName);
			}
			toXML( childNode, nextNode );
		}		
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
