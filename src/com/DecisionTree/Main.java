
package com.DecisionTree;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.DecisionTree.BuildTree.TreeNode;
import com.DecisionTree.TextProcessing.Attribute;
import com.DecisionTree.TextProcessing.Situation;


public class Main {	
	
	public static void main(String[] args) throws Exception {
		
		TextProcessing TextPro = new TextProcessing();
		TextPro.readText(new File("test1.csv"));
		InformationGain infoGain = new InformationGain();
		
		BuildTree DecisionTree = new BuildTree();
		TreeNode root = DecisionTree.creat(TextPro, null, null);
		System.out.println("------------show--------------------");
		DecisionTree.show(root);
		/*String MaxGainName = infoGain.getMaxGain(TextPro);
		for ( Attribute objAttr:TextPro.objAttributeList ) {
			System.out.println("Attribute Name = " + objAttr.AttributeName);
			if ( objAttr.AttributeName.equals(MaxGainName) ) {
				for ( String SituName:objAttr.SituationMap.keySet() ) {
					System.out.println("Situation Name = " + SituName);
					Situation objSitu = objAttr.SituationMap.get(SituName);
					System.out.println("\t\t" + objSitu.Result_Count.size());
				}
			}
		}
		TextPro.getChildTable(MaxGainName, "sunny");
		*/
	}

}
