
package com.DecisionTree;

import java.io.File;

import com.DecisionTree.BuildTree.TreeNode;


public class Main {	
	
	public static void main(String[] args) throws Exception {
		
		TextProcessing TextPro = new TextProcessing();
		TextPro.readText(new File("test.csv"));
		
		BuildTree DecisionTree = new BuildTree();
		TreeNode root = DecisionTree.creat(TextPro, null, null);
		System.out.println("------------show--------------------");
		DecisionTree.show(root);
		SaveTree objSave = new SaveTree();
		objSave.save( root );
	}

}
