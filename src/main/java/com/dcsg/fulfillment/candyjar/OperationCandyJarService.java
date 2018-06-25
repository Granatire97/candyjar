package com.dcsg.fulfillment.candyjar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationCandyJarService {
	
	private @Autowired OperationCandyJarRepository repo;
	private @Autowired OperationCandyJarConfiguration config;
	
	public ProductCodeMap getByEcode(String eCode) {
		return repo.getByEcode(eCode);
	}
	public ProductCodeMap getByStyle(String Style) {
		return repo.getByStyle(Style);
	}
	public ProductCodeMap getBySku(String Sku) {
		return repo.getBySku(Sku);
	}
	public ProductCodeMap getByUpc(String Upc) {
		return repo.getByUpc(Upc);
	}

	public List<String> getSkuHistory(String sku) {
		RemoteCommandExecuter rce = new RemoteCommandExecuter(config.getUnixUsername(), config.getUnixPassword(), config.getUnixHost(), 22);
		String command = "grep -hn \":\\\"" + sku + "\\\"\" ../../apps/syncatc/log/SyncATC.network.activity.*";
		List<String> lines = rce.executeCommand(command);
		for(int i = 0; i < lines.size(); i++) {
			lines.set(i, lines.get(i).split(" ")[2]);
		}
		return lines;
	}
	/*
	public List<String> getSkuQuantity(String sku){
		RemoteCommandExecuter rce = new RemoteCommandExecuter(config.getUnixUsername(), config.getUnixPassword(), config.getUnixHost(), 22);
		String command = "grep -hn \":\\\"" + sku + "\\\"\" ../../apps/syncatc/log/SyncATC.network.activity.*";
		//String command = "grep" + sku + "InventorySync_20180625010014111.GG_ECOM.csv";
		//find . -mtime -1 -exec grep -i "90556697" {} \; -print
		
	}
	*/
	
}
