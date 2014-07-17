
package com.DecisionTree;

import java.util.ArrayList;

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
	
	//信息增益计算
	//Entropy_S为计算信息增益的必须条件，所以首先判断其是否为空，为空则无法进行后续计算
	//参数CalcuParam的组织格式为"所计算的信息增益的属性的某个情况拥有的记录个数 这种情况计算得到的墒值"
	//中间以空格分隔，第一重循环统计总记录个数
	//第二重循环首先计算当前情况的记录个数在总记录个数中的比重
	//然后用其比重乘以这种情况墒值，累计求和到TotalGain
	//最后用Entropy_S减去TotalGain即得到当前属性的信息增益
	public double getInformationGain( ArrayList<String> CalcuParam ) {
		
		if ( this.Entropy_S == null ) {
			System.out.println("Entropy_S is null");
			return 0;
		}
		
		int 	TotalCount = 0;
		double 	TotalGain = 0;
		for ( String SingleString:CalcuParam ) {
			TotalCount += new Integer(SingleString.split(" ")[0]);
		}
		for ( String SingleString:CalcuParam ) {
			String SingleParam[] = SingleString.split(" ");
			Integer SingleCount = new Integer(SingleParam[0]);
			double Proportion = (double)SingleCount/(double)TotalCount;
			double SingleGain = -(Proportion)*(new Double(SingleParam[1]));
			TotalGain += SingleGain;
		}
		TotalGain = this.Entropy_S + TotalGain;
		return TotalGain;
	}
	
	//计算分类信息
	//参数为属性中各记录的出现个数
	//分别计算得到该记录个数作占的比例
	//通过公式-(p)*log(p)
	//累加获得分类信息
	public double getInformationSplit( ArrayList<Integer> CalcuParam )  {
		
		int TotalCount = 0;
		double TotalSplit = 0;
		for ( Integer SingleCount:CalcuParam ) {
			TotalCount += SingleCount;
		}
		for ( Integer SingleCount:CalcuParam ) {
			double Proportion = (double)SingleCount/(double)TotalCount;
			double Logarithmic = Math.log(Proportion)/Math.log(2);
			double SingleSplit = -(Proportion)*(Logarithmic);
			TotalSplit += SingleSplit;
		}
		return TotalSplit;
	}
	
	//计算信息增益率
	//参数AttrGain为该属性的信息增益，AttrSplit为该属性的分类信息
	//公式GainRatio=Gain/Split
	public double getInformationGainRation( Double AttrGain, Double AttrSplit ) {
		
		double GainRatio  = AttrGain/AttrSplit;
		return GainRatio;
	}
}
