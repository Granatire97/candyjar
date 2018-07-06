package com.dcsg.fulfillment.candyjar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(value = "file:./src/test/resources/application.properties")
public class OperationCandyJarRepositoryTests {
	
	private @Autowired OperationCandyJarRepository repo;
	private List<OperationCandyJarResult> resultRow = new ArrayList<OperationCandyJarResult>();;
	
	@Test
	public void testGetByEcodeNew() throws IOException {
	
		OperationCandyJarResult row = new OperationCandyJarResult();
		row.setECode("DFJACJGNAOAKJHAM");
		row.setStyle("462005");
		row.setSKU("40213892");
		row.setUPC("38495178345");
		row.setSUPC("38495178345");
		row.setPresale("1");
		row.setPresaleEndDate("Y");
		row.setHotMarket("0");
		row.setHotMarketEndDate("N");
		row.setSpecialOrder("0");
		row.setVDCEligible("N");
		resultRow.add(row);
		
		assertTrue(row.equals(repo.getByEcode("DFJACJGNAOAKJHAM").get(0)));
	}
	
	@Test
	public void testGetByStyle() throws IOException {
	
		OperationCandyJarResult row = new OperationCandyJarResult();
		row.setECode("DFJACJGNAOAKJHAM");
		row.setStyle("462005");
		row.setSKU("40213892");
		row.setUPC("38495178345");
		row.setSUPC("38495178345");
		row.setPresale("1");
		row.setPresaleEndDate("Y");
		row.setHotMarket("0");
		row.setHotMarketEndDate("N");
		row.setSpecialOrder("0");
		row.setVDCEligible("N");
		resultRow.add(row);
		
		assertTrue(row.equals(repo.getByStyle("462005").get(0)));
	}
	
	@Test
	public void testGetBySKU() throws IOException {
	
		OperationCandyJarResult row = new OperationCandyJarResult();
		row.setECode("DFJACJGNAOAKJHAM");
		row.setStyle("462005");
		row.setSKU("40213892");
		row.setUPC("38495178345");
		row.setSUPC("38495178345");
		row.setPresale("1");
		row.setPresaleEndDate("Y");
		row.setHotMarket("0");
		row.setHotMarketEndDate("N");
		row.setSpecialOrder("0");
		row.setVDCEligible("N");
		resultRow.add(row);
		
		assertTrue(row.equals(repo.getBySku("40213892").get(0)));
	}
	
	@Test
	public void testGetByUPC() throws IOException {
	
		OperationCandyJarResult row = new OperationCandyJarResult();
		row.setECode("DFJACJGNAOAKJHAM");
		row.setStyle("462005");
		row.setSKU("40213892");
		row.setUPC("38495178345");
		row.setSUPC("38495178345");
		row.setPresale("1");
		row.setPresaleEndDate("Y");
		row.setHotMarket("0");
		row.setHotMarketEndDate("N");
		row.setSpecialOrder("0");
		row.setVDCEligible("N");
		resultRow.add(row);
		
		assertTrue(row.equals(repo.getByUpc("38495178345").get(0)));
	}
	
	
}
