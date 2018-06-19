package com.dcsg.fulfillment.candyjar;

import java.io.IOException;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class OperationCandyJarController {

	public TreeMap<String, TreeMap<String, TreeMap<String, TreeSet<String>>>> expectedMap;
	
	@RequestMapping(path = "/test")
	@ResponseBody
	public String getTest() throws IOException {
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
		
		ObjectMapper objectMapper = new ObjectMapper();

		String json = objectMapper.writeValueAsString(expectedMap);
		
		return json;
	}
}
