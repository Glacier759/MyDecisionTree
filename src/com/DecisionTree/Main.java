
package com.DecisionTree;

import java.io.File;
import java.security.KeyStore.Entry.Attribute;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {	
	
	public static void main(String[] args) throws Exception {
	
		TextProcessing TextPro = new TextProcessing();
		TextPro.readFile(new File("test.csv"));
		
		ArrayList<Integer> ExtropyS_Param = new ArrayList<Integer>();
		
		
		/*TextPro.readFile(new File("test.csv"));
		TextPro.initResultSet();
			
		ArrayList<Integer> ExtropyS_Param = new ArrayList<Integer>();
		HashMap<String, Integer> lastResultSet = TextPro.ResultSet.get(TextPro.ResultName).AttributeSet;
		HashMap<String, AttributeClass> thisResultSet = TextPro.ResultSet;
		for ( String Situation:lastResultSet.keySet() ) {
			ExtropyS_Param.add(lastResultSet.get(Situation));
		}
		
		InformationGain infoGain = new InformationGain(ExtropyS_Param);
		ArrayList<String> GainCount = new ArrayList<String>();
		thisResultSet.remove(TextPro.ResultName);
		
		for ( String Header:thisResultSet.keySet() ) {
			HashMap<String, Integer> headerResultSet = thisResultSet.get(Header).AttributeSet;
			Integer TotalCount = 0;
			for ( String Situation:headerResultSet.keySet() ) {
				ArrayList<Integer> obj = new ArrayList<Integer>();
				Integer SingleCount = headerResultSet.get(Situation);
				obj.add(SingleCount);
				TotalCount += SingleCount;
			}
			GainCount.add(TotalCount+" "+obj);
		}
		
		ArrayList<Integer> Wind = new ArrayList<Integer>();
		ArrayList<Integer> Weak = new ArrayList<Integer>();
		ArrayList<Integer> Strong = new ArrayList<Integer>();
		Wind.add(9);	Wind.add(5);
		Weak.add(6); 	Weak.add(2);
		Strong.add(3); 	Strong.add(3);
		
		
		GainTest.add(6+" "+infoGain.getInformationEntropy(Strong));
		GainTest.add(8+" "+infoGain.getInformationEntropy(Weak));
		
		double Entropy_S = infoGain.getInformationEntropy(Wind);
		infoGain.Entropy_S = Entropy_S;
		System.out.println(infoGain.getInformationGain(GainTest));*/
	}

}
