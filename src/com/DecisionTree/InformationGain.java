
package com.DecisionTree;

import java.util.ArrayList;
import java.util.HashMap;

public class InformationGain {
	
	//墒值
	//Entropy_S = -(p+)*log(p+)-(p-)*log(p-)
	//p+、p-分别为正例和负例占总记录的比例，输出属性值大于2的情况，公示是对称的
	public Double Entropy_S = null; 
	
	public InformationGain() {
		
	}
	
	//踢狗构造方法，在实例化时直接计算得到Entropy_S
	public InformationGain(ArrayList<Integer> CalcuParam) {
		this.Entropy_S = this.getInformationEntropy(CalcuParam);
	}
	
	//计算墒值
	//参数CalcuParam表示每条属性所包含的记录个数
	//程序通过第一次循环计算得到总记录个数
	//通过第二次循环依次获得每条属性的记录个数在总记录个数中的比例
	//然后依据公式：-(p)*log(p)计算
	//将每次循环的计算结果累加到TotalEntropy上即得到结果
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
	
	//
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
