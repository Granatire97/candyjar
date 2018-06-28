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
		
		System.out.println(lines);
		for(int i = 0; i < lines.size(); i++) {
			StringBuilder newLine = new StringBuilder(lines.get(i).split(" ")[2]);
			newLine.insert(newLine.length()-15, '-');
			newLine.insert(newLine.length()-13, '-');
			newLine.insert(newLine.length()-11, ' ');
			newLine.insert(newLine.length()-9, ':');
			newLine.insert(newLine.length()-7, ':');
			newLine.insert(newLine.length()-5, '.');
			lines.set(i, newLine.toString());		
		}
		return lines;
	}
	
	public List<HashMap> getSkuAvailableQuantity(String sku){
		RemoteCommandExecuter rce = new RemoteCommandExecuter(config.getUnixUsername(), config.getUnixPassword(), config.getUnixHost(), 22);
		
		String command = "find ../../apps/filepolling/DSG.EOM.WCS.InventorySync/archive -mtime -1 -exec grep -ih \"" + sku + "\" {} \\;";
		
		List<String> lines = rce.executeCommand(command);
		List<HashMap> entries = new ArrayList<HashMap>();
		String [ ] keys = {"SKU", "Store Number", "Inventory Status", "Available Quantity"};
		
		for (int i = 0; i < lines.size(); i++) {
			HashMap<String, String> data = new HashMap<String, String>();
			String [] item = lines.get(i).split(",");
			for(int j = 0; j < item.length; j++) {
				data.put(keys[j].replaceAll(" ", ""), item[j]);
			}
			entries.add(data);
		}
		
		
		return entries;
	}
	
	
}
