package com.dcsg.fulfillment.candyjar;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class OperationCandyJarController {

	public @Autowired OperationCandyJarService service;
	
	@RequestMapping(path = "/eCode")
	@ResponseBody
	public String getByEcode2(@RequestParam(value="eCode") String eCode) throws IOException, SQLException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(service.getByEcode(eCode));	
		return json;
	}
	
	@RequestMapping(path = "/style")
	@ResponseBody
	public String getByStyle(@RequestParam(value="style") String style) throws IOException {
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(service.getByStyle(style));
		return json;
	}
	
	@RequestMapping(path = "/sku")
	@ResponseBody
	public String getBySku(@RequestParam(value="sku") String sku) throws IOException {
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(service.getBySku(sku));
		System.out.println(json);
		return json;
	}
	
	@RequestMapping(path = "/upc")
	@ResponseBody
	public String getByUPC(@RequestParam(value="upc") String upc) throws IOException {
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(service.getByUpc(upc));	
		return json;
	}
	
	
	@RequestMapping(path = "/SkuHistory")
	@ResponseBody
	public String geSkuHistory(@RequestParam(value="sku") String sku) throws IOException {

		return service.getSkuHistory(sku).toString();
	}
	
	@RequestMapping(path = "/SkuAvailableQuantity")
	@ResponseBody
	public String getSkuAvailableQuantity(@RequestParam(value="sku") String sku) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(service.getSkuAvailableQuantity(sku));
		return json;
	}
	
	
	
}
