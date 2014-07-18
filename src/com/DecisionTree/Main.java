
package com.DecisionTree;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.DecisionTree.TextProcessing.Attribute;
import com.DecisionTree.TextProcessing.Situation;


public class Main {	
	
	public static void main(String[] args) throws Exception {
		
		TextProcessing TextPro = new TextProcessing();
		TextPro.readText(new File("test1.csv"));
		InformationGain infoGain = new InformationGain();
		
		String MaxGainName = infoGain.getMaxGain(TextPro);
		for ( Attribute objAttr:TextPro.objAttributeList ) {
			System.out.println("Attribute Name = " + objAttr.AttributeName);
			if ( objAttr.AttributeName == MaxGainName ) {
				for ( String SituName:objAttr.SituationMap.keySet() ) {
					System.out.println("Situation Name = " + SituName);
					Situation objSitu = objAttr.SituationMap.get(SituName);
					System.out.println("\t\t" + objSitu.Result_Count.size());
				}
			}
		}
		TextPro.getChildTable(MaxGainName, "sunny");
		
	}

}
