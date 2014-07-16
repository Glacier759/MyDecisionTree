
package com.DecisionTree;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;


public class TextProcessing {
	
	public class AttributeClass {
		String AttributeName; 							//保存属性名称
		HashMap<String, SituationClass> SituationMap = new HashMap<String, SituationClass>(); 	//保存记录的名称与记录信息
	}
	public class SituationClass {
		Integer SituationCount; 					//保存记录总数
		HashMap<String, Integer> SituationType = new HashMap<String, Integer>(); 	//保存记录的结果种类与此类的个数
	}
	
	ArrayList<AttributeClass> ResultList = new ArrayList<AttributeClass>(); 	//记录除了最后一列的所有数据
	AttributeClass lastResult = new AttributeClass(); 							//记录最后一列的数据
	public void readFile( File file ) throws Exception {
		List<String> textList = FileUtils.readLines(file);
		String Headers[] = textList.remove(0).split(",");
		for ( int i = 0; i < Headers.length-1; i ++ ) {
			AttributeClass objAttr = new AttributeClass();
			objAttr.AttributeName = Headers[i];
			ResultList.add(objAttr);
		}
		for ( String textLine:textList ) {
			String textArray[] = textLine.split(",");
			for ( int i = 0; i < textArray.length-1; i ++ ) {
				HashMap<String, SituationClass> TempList = ResultList.get(i).SituationMap;
				if ( !TempList.containsKey(textArray[i]) ) {
					SituationClass objSitu = new SituationClass();
					objSitu.SituationCount = 1;
					String textLast = textArray[textArray.length-1];
					objSitu.SituationType.put(textLast, 1);
					TempList.put(textArray[i], objSitu);
				}
				else {
					SituationClass objSitu = TempList.get(textArray[i]);
					objSitu.SituationCount++;
					String textLast = textArray[textArray.length-1];
					if ( !objSitu.SituationType.containsKey(textArray[textArray.length-1]) ) {
						objSitu.SituationType.put(textLast, 1);
					}
					else {
						objSitu.SituationType.put(textLast, objSitu.SituationType.get(textLast)+1);
					}
				}
			}
		}
		lastResult.AttributeName = Headers[Headers.length-1];
		for ( String textLine:textList ) {
			String textLast = textLine.split(",")[Headers.length-1];
			HashMap<String, SituationClass> TempList = lastResult.SituationMap;
			if ( !TempList.containsKey(textLast) ) {
				SituationClass objSitu = new SituationClass();
				objSitu.SituationCount = 1;
				objSitu.SituationType.put(textLast, 1);
				TempList.put(textLast, objSitu);
			}
			else {
				SituationClass objSitu = TempList.get(textLast);
				objSitu.SituationCount++;
				objSitu.SituationType.put(textLast, objSitu.SituationType.get(textLast)+1);
			}
			
		}
		/*System.out.println(lastResult.AttributeName);
		for ( String Situstr:lastResult.SituationMap.keySet() ) {
			System.out.println(Situstr);
			SituationClass obj1 = lastResult.SituationMap.get(Situstr);
			System.out.println(obj1.SituationCount);
			for ( String str:obj1.SituationType.keySet() ) {
				System.out.println(str);
				System.out.println(obj1.SituationType.get(str));
			}
		}*/
		/*for ( AttributeClass obj:ResultList ) {
			System.out.println(obj.AttributeName);
			for ( String Situstr:obj.SituationMap.keySet() ) {
				System.out.println(Situstr);
				SituationClass obj1 = obj.SituationMap.get(Situstr);
				System.out.println(obj1.SituationCount);
				for ( String str:obj1.SituationType.keySet() ) {
					System.out.println(str);
					System.out.println(obj1.SituationType.get(str));
				}
			}
		}*/
	}
	/*
	//定义内部类，保存各属性中的信息
	//String用于保存当前属性的记录名
	//Integer用于保存该记录出现的次数
	public class AttributeClass{
		public HashMap<String, Integer> AttributeSet = new HashMap<String, Integer>();
		public AttributeClass() {
		}
	}
	
	private List<String> fileText;
	private String Headers[];
	public String ResultName;
	public HashMap<String, AttributeClass> ResultSet = new HashMap<String, AttributeClass>();
	
	//读入文件
	public void readFile( File file ) throws Exception {
		fileText = FileUtils.readLines(file);
		String Header = fileText.remove(0);
		Headers = Header.split(",");
		this.ResultName = Headers[this.getAttributeCount()];
	}
	
	//获得属性个数
	public int getAttributeCount() {
		return Headers.length-1;
	}
	
	//初始化结果集HashMap<String, AttributeClass> ResultSet
	//String为属性名
	//AttributeClass储存属性信息
	public void initResultSet() {
		for ( int j = 0; j <= getAttributeCount(); j ++ ) {
			AttributeClass obj = new AttributeClass();
			ResultSet.put(Headers[j], obj);
		}
		for ( int i = 0; i < fileText.size(); i ++ ) {
			String lineArray[] = fileText.get(i).split(",");
			for ( int j = 0; j <= getAttributeCount(); j ++ ) {
				HashMap<String, Integer> thisAttributeSet = ResultSet.get(Headers[j]).AttributeSet;
				if ( !thisAttributeSet.containsKey(lineArray[j]) ) {
					thisAttributeSet.put(lineArray[j], 1);
				}
				else {
					thisAttributeSet.put(lineArray[j], thisAttributeSet.get(lineArray[j])+1);
				}
			}
		}
		/*System.out.println(ResultSet.size());
		for ( String Header:ResultSet.keySet() ) {
			System.out.println("ResultSet = " + Header);
			AttributeClass obj = ResultSet.get(Header);
			for ( String ttt:obj.AttributeSet.keySet() ) {
				System.out.println(ttt+"\t"+obj.AttributeSet.get(ttt));
			}
		}
	}*/
}
