package com.dcsg.fulfillment.candyjar;

import java.util.TreeMap;
import java.util.TreeSet;

public class ProductCodeMap  {
	
	public TreeMap<String, TreeMap<String, TreeMap<String, TreeSet<String>>>> pcm = new TreeMap<String, TreeMap<String, TreeMap<String, TreeSet<String>>>>();
	
	public void add(String eCode, String style, String sku, String upc) {
		
		if(eCode == null) eCode = "null";
		if(style == null) style = "null";
		if(sku == null) sku = "null";
		if(upc == null) upc = "null";
		
		if(pcm.containsKey(eCode)) {
			
			if(pcm.get(eCode).containsKey(style)) {
				
				if(pcm.get(eCode).get(style).containsKey(sku)) {
					pcm.get(eCode).get(style).get(sku).add(upc);
				} else {
					TreeSet skuSet = new TreeSet<String>();
					skuSet.add(upc);
					pcm.get(eCode).get(style).put(sku, skuSet);
				}
			} else {
				TreeMap styleMap = new TreeMap<String, TreeSet<String>>();
				TreeSet skuSet = new TreeSet<String>();
				
				skuSet.add(upc);
				styleMap.put(sku, skuSet);
				
				pcm.get(eCode).put(style, styleMap);
			}
			
		} else {
			TreeMap eCodeMap = new TreeMap<String, TreeMap<String, TreeSet<String>>>();
			TreeMap styleMap = new TreeMap<String, TreeSet<String>>();
			TreeSet skuSet = new TreeSet<String>();
			
			skuSet.add(upc);
			styleMap.put(sku, skuSet);
			eCodeMap.put(style, styleMap);
			pcm.put(eCode, eCodeMap);
		}
	}

}

