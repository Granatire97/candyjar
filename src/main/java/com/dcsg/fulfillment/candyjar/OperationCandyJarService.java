package com.dcsg.fulfillment.candyjar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationCandyJarService {
	
	private @Autowired OperationCandyJarRepository repo;
	private @Autowired OperationCandyJarConfiguration config;
	
	public List<OperationCandyJarResult> getByEcode(String eCode) throws IOException {
		return repo.getByEcode(eCode);
	}
	public List<OperationCandyJarResult> getByStyle(String Style) throws IOException {
		return repo.getByStyle(Style);
	}
	public List<OperationCandyJarResult> getBySku(String Sku) throws IOException {
		return repo.getBySku(Sku);
	}
	public List<OperationCandyJarResult> getByUpc(String Upc) throws IOException {
		return repo.getByUpc(Upc);
	}

	public List<String> getSkuHistory(String sku) {
		RemoteCommandExecuter rce = new RemoteCommandExecuter(config.getUnixUsername(), config.getUnixPassword(), config.getUnixHost(), 22);
		String command = "grep -hn \":\\\"" + sku + "\\\"\" ../../apps/syncatc/log/SyncATC.network.activity.*";
		List<String> lines = rce.executeCommand(command);
		
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
		String command1 = "grep \"" + sku + "\" ../../apps/filepolling/DSG.EOM.WCS.InventorySync/archive/InventorySync_*.DSG_ECOM.csv";
		
		List<String> lines = rce.executeCommand(command1);
		List<HashMap> entries = new ArrayList<HashMap>();
		String [ ] keys = {"SKU", "Store Number", "Inventory Status", "Available Quantity"};
		
		for (int i = 0; i < lines.size(); i++) {
			HashMap<String, String> data = new HashMap<String, String>();
			String time = lines.get(i).split("_")[1].split("\\.")[0];
			StringBuilder timestamp = new StringBuilder(time);
			timestamp.insert(4,'-');
			timestamp.insert(7,'-');
			timestamp.insert(10,' ');
			timestamp.insert(13,':');
			timestamp.insert(16,':');
			timestamp.insert(19,'.');
			data.put("Time", timestamp.toString());
			String [] item = lines.get(i).split(":")[1].split(",");
			for(int j = 0; j < item.length; j++) {
				data.put(keys[j].replaceAll(" ", ""), item[j]);
			}
			entries.add(data);
		}
		return entries;
	}
	
	
}
