
package com.DecisionTree;

import java.io.FileWriter;
import java.io.Writer;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

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
		OutputFormat format = OutputFormat.createPrettyPrint();
		Writer fileWriter = new FileWriter("output.xml");
		XMLWriter output = new XMLWriter( fileWriter, format );
		output.write(xmlDoc);
		output.close();
		
		//FileUtils.writeStringToFile(new File("output.xml"), xmlDoc.asXML());
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
}
