package com.dcsg.fulfillment.candyjar;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public class OperationCandyJarRepositoryTests {
	
	private ProductCodeMap pcm;
	private @Autowired OperationCandyJarRepository repo;
	

	@BeforeEach
	public void setupBeforeEach() {
		pcm = new ProductCodeMap();	
	}
	
	public void testGetByEcode() {
		pcm.add("1234", "172829", "83435", "238748374");
		pcm.add("1234", "23843", "2387438", "3483933330");
		pcm.add("1234", "1723423", "833234", "238434374");
		pcm.add("1234", "172829", "83435", "238434374");
		pcm.add("1234", "23843", "235643", "344565455550");
		
		assertEquals(pcm, repo.getByEcode("1234"));
	}
	public void testGetByStyle() {
		pcm.add("1235", "461997", "83435", "238748374");
		pcm.add("1235", "461997", "2387438", "3483933330");
		pcm.add("1235", "461997", "833234", "238434374");
		pcm.add("1235", "461997", "83435", "238434374");
		pcm.add("1235", "461997", "235643", "344565455550");
		
		assertEquals(pcm, repo.getByStyle("461997"));
	}
	public void testGetBySku() {
		pcm.add("1236", "461998", "4021891", "238748374");
		pcm.add("1236", "461998", "4021891", "3483933330");
		pcm.add("1236", "461998", "4021891", "238434374");
		pcm.add("1236", "461998", "4021891", "238434374");
		pcm.add("1236", "461998", "4021891", "344565455550");
		
		assertEquals(pcm, repo.getBySku("4021891"));
	}
	public void testGetByUpc() {
		pcm.add("1237", "462005", "4021892", "3849578345");
		
		assertEquals(pcm, repo.getByUpc("3849578345"));
	}
	
}
