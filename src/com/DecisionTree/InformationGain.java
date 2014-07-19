
package com.DecisionTree;

import java.util.ArrayList;
import java.util.HashMap;

import com.DecisionTree.TextProcessing.Attribute;

public class InformationGain {
	
	public InformationGain() {
		
	}
	
	public double getInformationEntroy( HashMap<String, Integer> CalcuParam ) {
		int TotalCount = 0;
		double TotalEntroy = 0;
		
		for ( String CalcuName:CalcuParam.keySet() ) {
			int SingleCount = CalcuParam.get(CalcuName);
			TotalCount += SingleCount;
		}
		for ( String CalcuName:CalcuParam.keySet() ) {
			int SingleCount = CalcuParam.get(CalcuName);
			double Proportion = (double)SingleCount/(double)TotalCount;
			double Logarithmic = Math.log(Proportion)/Math.log(2);
			double SingleEntroy = -(Proportion)*(Logarithmic);
			TotalEntroy += SingleEntroy;
		}
		return TotalEntroy;
	}
	
	public double getInformationGain( double Entropy_S, ArrayList<Integer> CalcuParamInt, ArrayList<Double> CalcuParamDou ) {
		int TotalCount = 0;
		double TotalGain = 0;
		for ( Integer SingleCount:CalcuParamInt ) {
			TotalCount += SingleCount;
		}
		for ( int i = 0; i < CalcuParamInt.size(); i ++ ) {
			int SingleCount = CalcuParamInt.get(i);
			double Proportion = (double)SingleCount/(double)TotalCount;
			double SingleEntropy = (Proportion)*(CalcuParamDou.get(i));
			TotalGain += SingleEntropy;
		}
		
		TotalGain = Entropy_S - TotalGain;
		return TotalGain;
	}
	

	public double getInformationSplit( ArrayList<Integer> CalcuParam ) {
		int TotalCount = 0;
		double TotalSplit = 0;
		for ( Integer SingleCount:CalcuParam ) {
			TotalCount += SingleCount;
		}
		for ( Integer SingleCount:CalcuParam ) {
			double Proportion = (double)SingleCount/(double)TotalCount;
			double Logarithmic = Math.log(Proportion)/Math.log(2);
			double SingleSplit = (Proportion)*(Logarithmic);
			TotalSplit += SingleSplit;
		}
		TotalSplit = 0 - TotalSplit;
		return TotalSplit;
	}
	
	public double getInformationGainRatio( double Gain, double Split ) {
		return (Gain/Split);
	}
	
	public String getMaxGain( TextProcessing TextPro ) {
		double Entropy_S = getInformationEntroy(TextPro.objResult.Result_Count);
		HashMap<String, Double> GainMap = new HashMap<String, Double>();
		for ( int i = 0; i < TextPro.objAttributeList.size(); i ++ ) {
			Attribute objAttr = TextPro.objAttributeList.get(i);
			ArrayList<Integer> CalcuParamInt = new ArrayList<Integer>();
			ArrayList<Double> CalcuParamDou = new ArrayList<Double>();
			for ( String SituName:objAttr.Situation_Count.keySet() ) {
				CalcuParamInt.add(objAttr.Situation_Count.get(SituName));
				CalcuParamDou.add(getInformationEntroy(objAttr.SituationMap.get(SituName).Result_Count));
			}
			double Gain = getInformationGain(Entropy_S, CalcuParamInt, CalcuParamDou);
			double Split = getInformationSplit(CalcuParamInt);
			double GainRatio = getInformationGainRatio(Gain, Split);
			GainMap.put(objAttr.AttributeName, GainRatio);
		}
		Double MaxGain = null;
		String MaxGainName = null;
		for ( String SingleGainName:GainMap.keySet()) {
			if ( MaxGain == null ) {
				MaxGain = GainMap.get(SingleGainName);
				MaxGainName = SingleGainName;
			}
			else {
				if ( MaxGain < GainMap.get(SingleGainName) ) {
					MaxGain = GainMap.get(SingleGainName);
					MaxGainName = SingleGainName;
				}
			}
		}
		return MaxGainName;
	}
}
