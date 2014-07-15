
package com.DecisionTree;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Main {
	
	
	
	public static void main(String[] args) throws Exception {
		List<String> TrainingInfo = new ArrayList<String>();
		
		TrainingInfo = FileUtils.readLines(new File("test.csv"));
		String TrainingHeader = TrainingInfo.remove(0);
		String Headers[] = TrainingHeader.split(",");
		
		InformationGain infoGain = new InformationGain();
		ArrayList<Integer> Test = new ArrayList<Integer>();
		Test.add(3);
		Test.add(4);
		System.out.println(infoGain.getInformationEntropy(Test));
		
		System.out.println(TrainingInfo.size());
		for ( String Training:TrainingInfo ) {
			System.out.println(Training);
		}
		for ( String Head:Headers ) {
			System.out.println(Head);
		}
	}

}
