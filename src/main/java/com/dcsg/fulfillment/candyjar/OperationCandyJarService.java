package com.dcsg.fulfillment.candyjar;

import java.util.List;

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
		String command  = "grep -r \"" + sku + "\" ../../apps/syncatc/log/";
		return rce.executeCommand(command);
	}
}
