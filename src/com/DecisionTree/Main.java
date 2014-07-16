
package com.DecisionTree;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Main {
	
	
	
	public static void main(String[] args) throws Exception {
		List<String> TrainingInfo = new ArrayList<String>();
		
		TrainingInfo = FileUtils.readLines(new File("test.csv"));
		String TrainingHeader = TrainingInfo.remove(0);
		String Headers[] = TrainingHeader.split(",");
		
		InformationGain infoGain = new InformationGain();
		//ArrayList<Integer> Test = new ArrayList<Integer>();
		//Test.add(3);
		//Test.add(4);
		//System.out.println(infoGain.getInformationEntropy(Test));
		
		ArrayList<Integer> Wind = new ArrayList<Integer>();
		ArrayList<Integer> Weak = new ArrayList<Integer>();
		ArrayList<Integer> Strong = new ArrayList<Integer>();
		Wind.add(9);
		Wind.add(5);
		Weak.add(6);
		Weak.add(2);
		Strong.add(3);
		Strong.add(3);
		
		ArrayList<String> GainTest = new ArrayList<String>();
		GainTest.add(6+" "+infoGain.getInformationEntropy(Strong));
		GainTest.add(8+" "+infoGain.getInformationEntropy(Weak));
		
		double Entropy_S = infoGain.getInformationEntropy(Wind);
		infoGain.Entropy_S = Entropy_S;
		System.out.println(infoGain.getInformationGain(GainTest));
		/*System.out.println(TrainingInfo.size());
		for ( String Training:TrainingInfo ) {
			System.out.println(Training);
		}
		for ( String Head:Headers ) {
			System.out.println(Head);
		}*/
	}

}
