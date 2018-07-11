package com.dcsg.fulfillment.candyjar;



public class OperationCandyJarResult {

		private String eCode;
		private String style;
		private String sku;
		private String upc;
		private String sUPC;
		private String description;
		private String presale;
		private String presaleEndDate;
		private String hotMarket;
		private String hotMarketEndDate;
		private String specialOrder;
		private String vdcEligible;
		
		public String getECode() {
		    return eCode;
		}
		
		public String getStyle() {
		    return style;
		}
		
		public String getSKU() {
		    return sku;
		}
		
		public String getUPC() {
		    return upc;
		}
		
		public String getSUPC() {
		    return sUPC;
		}
		
		public String getDescription() {
			return description;
		}
		
		public String getPresale() {
		    return presale;
		}
		
		public String getPresaleEndDate() {
		    return presaleEndDate;
		}
		
		public String getHotMarket() {
		    return hotMarket;
		}
		
		public String getHotMarketEndDate() {
		    return hotMarketEndDate;
		}
		
		public String getSpecialOrder() {
		    return specialOrder;
		}
		
		public String getVDCEligible() {
		    return vdcEligible;
		}
				
		public void setECode(String eCode) {
		    this.eCode = eCode;
		}
		
		public void setStyle(String style) {
		    this.style = style;
		}
		
		public void setSKU(String sku) {
		    this.sku = sku;
		}
		
		public void setUPC(String upc) {
			this.upc = upc;
		}
		
		public void setSUPC(String sUPC) {
			this.sUPC = sUPC;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
		
		public void setPresale(String presale) {
		    this.presale = presale;
		}
		
		public void setPresaleEndDate(String presaleEndDate) {
		    this.presaleEndDate = presaleEndDate;
		}
		
		public void setHotMarket(String hotMarket) {
		    this.hotMarket = hotMarket;
		}
		
		public void setHotMarketEndDate(String hotMarketEndDate) {
		    this.hotMarketEndDate = hotMarketEndDate;
		}
		
		public void setSpecialOrder(String specialOrder) {
		    this.specialOrder = specialOrder;
		}
		
		public void setVDCEligible(String vdcEligible) {
		    this.vdcEligible = vdcEligible;
		}
		
		
		public boolean equals(OperationCandyJarResult result) {

			return (this.getECode().compareTo(result.getECode()) == 0 &&
					this.getStyle().compareTo(result.getStyle()) == 0 &&
					this.getSKU().compareTo(result.getSKU()) == 0 &&
					this.getUPC().compareTo(result.getUPC()) == 0 &&
					this.getSUPC().compareTo(result.getSUPC()) == 0 &&
					this.getDescription().compareTo(result.getDescription()) == 0 &&
					this.getPresale().compareTo(result.getPresale()) == 0 &&
					this.getPresaleEndDate().compareTo(result.getPresaleEndDate()) == 0 &&
					this.getHotMarket().compareTo(result.getHotMarket()) == 0 &&
					this.getHotMarketEndDate().compareTo(result.getHotMarketEndDate()) == 0 &&
					this.getSpecialOrder().compareTo(result.getSpecialOrder()) == 0 &&
					this.getVDCEligible().compareTo(result.getVDCEligible()) == 0	);
		}

	
	
	
}
