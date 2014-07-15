
package com.DecisionTree;

import java.util.ArrayList;

public class InformationGain {
	
	public InformationGain() {
		
	}
	
	public double getInformationEntropy( ArrayList<Integer> CalcuParam ) {
		
		int 	TotalCount = 0;
		double 	TotalEntropy = 0;
		for ( int SingleCount:CalcuParam ) {
			TotalCount += SingleCount;
		}
		for ( int SingleCount:CalcuParam ) {
			double Proportion = (double)SingleCount/(double)TotalCount;
			double Logarithmic = Math.log(Proportion)/Math.log(2);
			double SingleEntropy = -(Proportion)*(Logarithmic);
			TotalEntropy += SingleEntropy;
		}
		return TotalEntropy;
	}
}
