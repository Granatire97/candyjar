package com.dcsg.fulfillment.candyjar;

import static org.junit.Assert.assertEquals;

import java.util.TreeSet;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ProductCodeMapTests {
	
	@Test
	public void testAdd() {
		TreeMap<String, TreeMap<String, TreeMap<String, TreeSet<String>>>> expectedMap;
		ProductCodeMap pcm = new ProductCodeMap();
		expectedMap = new TreeMap<String, TreeMap<String, TreeMap<String, TreeSet<String>>>>();
		TreeSet sku = new TreeSet<String>();
		sku.add("upc");
		sku.add("upc2");
		
		TreeSet sku2 = new TreeSet<String>();
		sku2.add("upc3");

		TreeMap style = new TreeMap<String, TreeMap<String, TreeSet<String>>>();
		style.put("sku", sku);
		style.put("sku2",sku2);
		
		TreeSet sku3 = new TreeSet<String>();
		sku3.add("upc4");
		sku3.add("upc5");
		
		TreeMap style2 = new  TreeMap<String, TreeSet<String>>();
		style2.put("sku3",sku3);
		
		TreeMap eCode = new  TreeMap<String, TreeMap<String, TreeSet<String>>>();
		eCode.put("style", style);
		eCode.put("style2", style2);
		
		expectedMap.put("ecode", eCode);
		
		pcm.add("ecode", "style", "sku", "upc");
		pcm.add("ecode", "style", "sku", "upc2");
		pcm.add("ecode", "style", "sku2", "upc3");
		pcm.add("ecode", "style2", "sku3", "upc4");
		pcm.add("ecode", "style2", "sku3", "upc5");
			
		assertEquals(expectedMap, pcm.pcm);
			
			
	}
	
	@Test
	public void testEmptyVaules() {
		TreeMap<String, TreeMap<String, TreeMap<String, TreeSet<String>>>> expectedMap;
		ProductCodeMap pcm = new ProductCodeMap();
		expectedMap = new TreeMap<String, TreeMap<String, TreeMap<String, TreeSet<String>>>>();
		
		TreeSet sku = new TreeSet<String>();
		sku.add("null");
		
		TreeSet sku2 = new TreeSet<String>();
		sku2.add("upc2");
		
		TreeMap style = new TreeMap<String, TreeMap<String, TreeSet<String>>>();
		style.put("sku", sku);
		
		TreeMap style2 = new  TreeMap<String, TreeSet<String>>();
		style2.put("sku2",sku2);
	
		TreeMap eCode = new  TreeMap<String, TreeMap<String, TreeSet<String>>>();
		eCode.put("style", style);
		eCode.put("null", style2);
		
		expectedMap.put("ecode", eCode);
		
		pcm.add("ecode","style", "sku", null);
		pcm.add("ecode", null, "sku2", "upc2");

		assertEquals(expectedMap, pcm.pcm);	
	}
	
	@Test 
	public void testAddList() {
		TreeMap<String, TreeMap<String, TreeMap<String, TreeSet<String>>>> expectedMap;
		ProductCodeMap pcm = new ProductCodeMap();
		expectedMap = new TreeMap<String, TreeMap<String, TreeMap<String, TreeSet<String>>>>();
		TreeSet sku = new TreeSet<String>();
		sku.add("upc");	

		TreeMap style = new TreeMap<String, TreeMap<String, TreeSet<String>>>();
		style.put("sku", sku);
		
		TreeMap eCode = new  TreeMap<String, TreeMap<String, TreeSet<String>>>();
		eCode.put("style", style);
		
		expectedMap.put("ecode", eCode);
		
		ArrayList<String> testProduct = new ArrayList<String>();
		testProduct.add("ecode");
		testProduct.add("style");
		testProduct.add("sku");
		testProduct.add("upc");
		pcm.addList(testProduct);
			
		assertEquals(expectedMap, pcm.pcm);
	}
	
	@Test
	public void testAddAll() {
		TreeMap<String, TreeMap<String, TreeMap<String, TreeSet<String>>>> expectedMap;
		ProductCodeMap pcm = new ProductCodeMap();
		expectedMap = new TreeMap<String, TreeMap<String, TreeMap<String, TreeSet<String>>>>();
		TreeSet sku = new TreeSet<String>();
		sku.add("upc");
		sku.add("upc2");

		TreeMap style = new TreeMap<String, TreeMap<String, TreeSet<String>>>();
		style.put("sku", sku);
		
		TreeMap eCode = new  TreeMap<String, TreeMap<String, TreeSet<String>>>();
		eCode.put("style", style);
		
		expectedMap.put("ecode", eCode);
		
		List<String> testProduct = new ArrayList<String>();
		testProduct.add("ecode");
		testProduct.add("style");
		testProduct.add("sku");
		testProduct.add("upc");
		
		List<String> testProduct2 = new ArrayList<String>();
		testProduct2.add("ecode");
		testProduct2.add("style");
		testProduct2.add("sku");
		testProduct2.add("upc2");
		
		List<List<String>> testProductList = new ArrayList<List<String>>();
		testProductList.add(testProduct);
		testProductList.add(testProduct2);
		
		
		pcm.addAll(testProductList);
			
		assertEquals(expectedMap, pcm.pcm);
	}
}
