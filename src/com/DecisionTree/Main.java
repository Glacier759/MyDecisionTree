
package com.DecisionTree;

import java.io.File;
import java.security.KeyStore.Entry.Attribute;
import java.util.ArrayList;
import java.util.HashMap;

import com.DecisionTree.TextProcessing.AttributeClass;
import com.DecisionTree.TextProcessing.SituationClass;

public class Main {	
	
	public static void main(String[] args) throws Exception {
	
		//读取文件并处理
		TextProcessing TextPro = new TextProcessing();
		TextPro.readFile(new File("test1.csv"));
		
		//计算得到EntropyS
		ArrayList<Integer> EntropyS_Param = new ArrayList<Integer>(); 	//初始化计算EntropyS的参数列表
		for ( String Situation:TextPro.lastResult.SituationMap.keySet() ) { 	//遍历结果集中所有的记录种类
			EntropyS_Param.add(TextPro.lastResult.SituationMap.get(Situation).SituationType.get(Situation)); 	//获得该种记录出现的次数
		}
		InformationGain infoGain = new InformationGain(EntropyS_Param); 	//实例化计算墒与信息增益的类 传参表示初始化同时进行EntropyS的计算
		System.out.println(infoGain.getInformationEntropy(EntropyS_Param)); 	//打印计算得到的EntropyS
		
		HashMap<String, Double> AttrGainMap = new HashMap<String, Double>();
		for ( AttributeClass objAttr:TextPro.ResultList  ) { 				//遍历其余所有属性
			ArrayList<Double> AttributeEntropy = new ArrayList<Double>(); 	//以每个属性为根节点，计算其信息增益，初始化所需的各记录的各种类的墒值
			ArrayList<Integer> AttributeCount = new ArrayList<Integer>(); 	//保存各属性的所有记录的个数s
			for ( String SituationName:objAttr.SituationMap.keySet() ) { 			//遍历当前属性的所有记录
				ArrayList<Integer> EntropyA_Param = new ArrayList<Integer>(); 		//初始化计算该记录墒值的参数列表
				SituationClass objSitu = objAttr.SituationMap.get(SituationName); 		//获得保存有当前记录的对象
				for ( String SituationType:objSitu.SituationType.keySet() ) { 			//遍历当前记录的所有类别
					EntropyA_Param.add(objSitu.SituationType.get(SituationType)); 		//将每个类别的个数加入参数列表
				}
				AttributeEntropy.add(infoGain.getInformationEntropy(EntropyA_Param)); 	//计算得到当前记录的墒值并存入列表中
				AttributeCount.add(objSitu.SituationCount);
			}
			//至此得到当前属性的所有记录的墒值 与 对应记录的个数
			ArrayList<String> GainCount = new ArrayList<String>(); 		//初始化计算当前属性的信息增益参数队列
			for ( int i = 0; i < AttributeEntropy.size(); i ++ ) { 		
				GainCount.add(AttributeCount.get(i)+" "+AttributeEntropy.get(i)); 	//将计算当前属性信息增益所需的参数加入参数列表
			}
			AttrGainMap.put(objAttr.AttributeName, infoGain.getInformationGain(GainCount)); 	//计算当前属性信息增益并保存在集合中
		}
		//至此得到所有属性的信息增益
		for ( String AttrName:AttrGainMap.keySet() ) {
			System.out.println(AttrName+"\t\t"+AttrGainMap.get(AttrName));
		}
		
		HashMap<String, Double> AttrSplitMap = new HashMap<String, Double>(); 		//保存各属性的分类信息
		for ( AttributeClass objAttr:TextPro.ResultList  ) { 				//遍历其余所有属性
			ArrayList<Integer> SplitList = new ArrayList<Integer>(); 	//初始化计算分类信息所需的参数列表
			for ( String SituName:objAttr.SituationMap.keySet() ) { 	//遍历当前属性的所有记录
				SituationClass objSitu = objAttr.SituationMap.get(SituName);
				SplitList.add(objSitu.SituationCount); 					//加入参数列表
			}
			AttrSplitMap.put(objAttr.AttributeName, infoGain.getInformationSplit(SplitList)); 	//计算分类信息并加入集合中
		}
		
		HashMap<String, Double> AttrGainRatioMap = new HashMap<String, Double>(); 	//初始化保存信息增益率的集合
		for ( String AttrName:AttrGainMap.keySet() ) { 								//遍历所有属性
			AttrGainRatioMap.put(AttrName, infoGain.getInformationGainRation(AttrGainMap.get(AttrName), AttrSplitMap.get(AttrName))); 	//计算信息增益率并加入集合
		}
		for ( String AttrName:AttrGainRatioMap.keySet() ) {
			System.out.println(AttrName+"\t"+AttrGainRatioMap.get(AttrName));
		}
		
	}

}
