package com.dcsg.fulfillment.candyjar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationCandyJarService {
	
	private @Autowired OperationCandyJarRepository repo;
	
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

}
