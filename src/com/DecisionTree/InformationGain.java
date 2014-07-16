
package com.DecisionTree;

import java.util.ArrayList;
import java.util.HashMap;

public class InformationGain {
	
	public Double Entropy_S = null;
	public InformationGain() {
		
	}
	
	public InformationGain(ArrayList<Integer> CalcuParam) {
		this.Entropy_S = this.getInformationEntropy(CalcuParam);
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
	
	public double getInformationGain( HashMap<Integer,Double> CalcuParam ) {
		
		if ( this.Entropy_S == null ) {
			System.out.println("Entropy_S is null");
			return 0;
		}
		
		int 	TotalCount = 0;
		double 	TotalGain = 0;
		for ( int SingleCount:CalcuParam.keySet() ) {
			TotalCount += SingleCount;
		}
		for ( Integer SingleCount:CalcuParam.keySet() ) {
			double Proportion = (double)SingleCount/(double)TotalCount;
			double SingleGain = -(Proportion)*CalcuParam.get(SingleCount);
			TotalGain += SingleGain;
		}
		TotalGain = this.Entropy_S + TotalGain;
		return TotalGain;
	}
}
