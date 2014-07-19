
package com.DecisionTree;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;


public class TextProcessing {
	class Result {
		public String ResultName;
		public ArrayList<String> ResultList = new ArrayList<String>();
		public HashMap<String, Integer> Result_Count = new HashMap<String, Integer>();
	}
	class Attribute {
		public String AttributeName;
		public HashMap<String, Situation> SituationMap = new HashMap<String, Situation>();
		public HashMap<String, Integer> Situation_Count = new HashMap<String, Integer>();
	}
	class Situation {
		public String SituationName;
		public HashMap<String, Integer> Result_Count = new HashMap<String, Integer>();
	}
	public Result objResult = new Result();
	public ArrayList<Attribute> objAttributeList = new ArrayList<Attribute>();
	public String Headers[];
	public List<String> textList = new ArrayList<String>();
	
	public void readText( File file ) throws Exception {
		List<String> textList = FileUtils.readLines(file);
		textProcess(textList);
	}
	public void readText( List<String> textList ) {
		textProcess(textList);
	}
	
	public void textProcess( List<String> textList ) {
		for ( String textLine:textList ) {
			this.textList.add(textLine);
		}
		Headers = textList.remove(0).split(",");
		for ( int i = 0; i < Headers.length-1; i ++ ) {
			Attribute objAttr = new Attribute();
			objAttr.AttributeName = Headers[i];
			objAttributeList.add(objAttr);
		}
		objResult.ResultName = Headers[Headers.length-1];
		
		for ( String textLine:textList ) {
			String[] textArray = textLine.split(",");
			int lastIndex = textArray.length-1;
			for ( int i = 0; i < lastIndex; i ++ ) {
				Attribute objAttr = objAttributeList.get(i);
				if ( !objAttr.Situation_Count.containsKey(textArray[i]) ) { 	//当textArray[i]这条属性第一次出现时
					objAttr.Situation_Count.put(textArray[i], 1);
					Situation objSitu = new Situation();
					objSitu.SituationName = textArray[i];
					if ( !objSitu.Result_Count.containsKey(textArray[lastIndex]) ) {
						objSitu.Result_Count.put(textArray[lastIndex], 1);
					}
					else {
						objSitu.Result_Count.put(textArray[lastIndex], objSitu.Result_Count.get(textArray[lastIndex])+1);
					}
					objAttr.SituationMap.put(textArray[i], objSitu);
				}
				else {
					objAttr.Situation_Count.put(textArray[i], objAttr.Situation_Count.get(textArray[i])+1);
					Situation objSitu = objAttr.SituationMap.get(textArray[i]);
					if ( !objSitu.Result_Count.containsKey(textArray[lastIndex]) ) {
						objSitu.Result_Count.put(textArray[lastIndex], 1);
					}
					else {
						objSitu.Result_Count.put(textArray[lastIndex], objSitu.Result_Count.get(textArray[lastIndex])+1);
					}
				}
			}
			if ( !objResult.Result_Count.containsKey(textArray[lastIndex]) ) {
				objResult.Result_Count.put(textArray[lastIndex], 1);
				objResult.ResultList.add(textArray[lastIndex]);
			}
			else {
				objResult.Result_Count.put(textArray[lastIndex], objResult.Result_Count.get(textArray[lastIndex])+1);
			}
		}
	}
	
	public List<String> getChildTable( String AttributeName, String SituationName ) {
		
		List<String> newTextList = new ArrayList<String>();
		String Headers[] = this.textList.get(0).split(",");
		int AttrIndex = 0;
		for ( int i = 0; i < Headers.length; i ++ ) {
			if ( AttributeName.equals(Headers[i]) ) {
				AttrIndex = i;
			}
		}
		newTextList.add(deleteIndex(AttrIndex, Headers));
		for ( int i = 1; i < this.textList.size(); i ++ ) {
			String textLine = this.textList.get(i);
			String textArray[] = textLine.split(",");
			if ( textArray[AttrIndex].equals(SituationName) ) {
				newTextList.add(deleteIndex(AttrIndex, textArray));
			}
		}
		return newTextList;
	}
	public String deleteIndex( int delIndex, String delArray[] ) {
		String str = "";
		for ( int i = 0; i < delArray.length; i ++ ) {
			if ( i == delIndex ) {
				continue;
			}
			str += ","+delArray[i];
		}
		str = str.substring(1);
		return str;
	}
	
	public void show() {
		System.out.println(objResult.ResultName+"\t"+objResult.ResultList.size());
		for ( String ResultName:objResult.Result_Count.keySet() ) {
			System.out.println("ResultName = " + ResultName +"\t" + objResult.Result_Count.get(ResultName));
		}
		for ( int i = 0; i < objResult.ResultList.size(); i ++ ) {
			System.out.println(objResult.ResultList.get(i));
		}
		
		for ( Attribute objAttr:objAttributeList ) {
			System.out.println(objAttr.AttributeName);
			for ( String AttrName:objAttr.Situation_Count.keySet() ) {
				System.out.println(AttrName+"\t"+objAttr.Situation_Count.get(AttrName));
			}
			for ( String AttrName:objAttr.SituationMap.keySet() ) {
				Situation objSitu = objAttr.SituationMap.get(AttrName);
				System.out.println(objSitu.SituationName);
				for ( String SituName:objSitu.Result_Count.keySet() ) {
					System.out.println(SituName+"\t"+objSitu.Result_Count.get(SituName));
				}
			}
		}
		
	}
}
