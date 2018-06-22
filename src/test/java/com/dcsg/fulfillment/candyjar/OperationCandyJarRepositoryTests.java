package com.dcsg.fulfillment.candyjar;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationCandyJarRepositoryTests {
	
	private @Autowired OperationCandyJarRepository repo;
	private ProductCodeMap pcm;
	
	@Test
	public void testGetByEcode() {
		pcm = new ProductCodeMap();
		pcm.add("1234", "172829", "83435", "238748374");
		pcm.add("1234", "23843", "2387438", "34839333301");
		pcm.add("1234", "1723423", "833234", "2384343742");
		pcm.add("1234", "172829", "83435", "2384343374");
		pcm.add("1234", "23843", "235643", "3445654545454550");
		
		assertEquals(pcm, repo.getByEcode("1234"));
	}
	@Test
	public void testGetByStyle() {
		pcm = new ProductCodeMap();
		pcm.add("1235", "461997", "83435", "238748374");
		pcm.add("1235", "461997", "2387438", "34839633330");
		pcm.add("1235", "461997", "833234", "2384237234374");
		pcm.add("1235", "461997", "83435", "23843454936374");
		pcm.add("1235", "461997", "235643", "344565844564550");
		
		assertEquals(pcm, repo.getByStyle("461997"));
	}
	@Test
	public void testGetBySku() {
		pcm = new ProductCodeMap();
		pcm.add("1236", "461998", "4021891", "24563800748374");
		pcm.add("1236", "461998", "4021891", "34839388345640");
		pcm.add("1236", "461998", "4021891", "23843466374");
		pcm.add("1236", "461998", "4021891", "23843444546374");
		pcm.add("1236", "461998", "4021891", "3445452264450");
		
		assertEquals(pcm, repo.getBySku("4021891"));
	}
	@Test
	public void testGetByUpc() {
		pcm = new ProductCodeMap();
		pcm.add("1237", "462005", "4021892", "38495178345");
		
		assertEquals(pcm, repo.getByUpc("38495178345"));
	}
	
}
