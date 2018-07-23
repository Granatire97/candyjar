package com.dcsg.fulfillment.candyjar;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class OperationCandyJarController {

	public @Autowired OperationCandyJarService service;
	private @Autowired OperationCandyJarConfiguration config;
	
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
	
	@RequestMapping(path = "/EsbLiveCount")
	@ResponseBody
	public String getEsbLiveCount(@RequestParam(value="sku") String sku) throws IOException {
		String url = config.getEsbUrl();
		String payload = "[{\"location\":\"0\", \"sku\": \"" + sku + "\"}]";
		System.out.println(payload);
        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_JSON);
        
        CredentialsProvider provider = new BasicCredentialsProvider();
          
        HttpClient client = HttpClientBuilder.create()
          .setDefaultCredentialsProvider(provider)
          .build();
        
        HttpPost request = new HttpPost(url);
        request.setEntity(entity);
        
        HttpResponse response = client.execute(request);
        
        return EntityUtils.toString(response.getEntity());
	}
	
	
	
}
