package com.dcsg.fulfillment.candyjar;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.sql.ResultSet;

@Repository
public class OperationCandyJarRepository {
	
    private @Autowired JdbcTemplate jdbcTemplate;
    
    public List<OperationCandyJarResult> getByEcode(String eCode) throws IOException{

    	String sql = "select SUBSTR(item_image_filename, instr(item_image_filename,'/',-1) + 1, REGEXP_INSTR(item_image_filename,'[?_]',1) - instr(item_image_filename,'/',-1) - 1) as eCode, item_style as Style, item_name as SKU, item_bar_code as UPC, supplier_item_barcode" 
    			+ " as sUPC, description as Description, "
    			+ " ref_field8 as  Presale, REF_DATE_FIELD3_DTTM as Presale_Date, ref_field11 as HotMarket, REF_DATE_FIELD2_DTTM as HotMarket_Date, ref_field7" 
    			+ " as Special_Order, ref_field38 as VDC_Eligible "
    			+ "from item_cbo "
    			+ "left join item_supplier_xref_cbo on item_supplier_xref_cbo.item_barcode = item_cbo.item_bar_code "
    			+ "where item_image_filename like ?"
    			+ "order by eCode, Style, SKU, UPC DESC";

    	List<OperationCandyJarResult> results = new ArrayList<OperationCandyJarResult>();

    	try {
    		results = jdbcTemplate.query(sql,  new Object[] {"%" + eCode + "%"}, new RowMapper<OperationCandyJarResult>(){
    			public OperationCandyJarResult mapRow(ResultSet rs, int rowNum)
    					throws SQLException{

    				OperationCandyJarResult result = new OperationCandyJarResult();	
    				result.setECode(rs.getString(1));
    				result.setStyle(rs.getString(2));
    				result.setSKU(rs.getString(3));
    				result.setUPC(rs.getString(4));
    				result.setSUPC(rs.getString(5));
    				result.setDescription(rs.getString(6));
    				result.setPresale(rs.getString(7));
    				result.setPresaleEndDate(rs.getString(8));
    				result.setHotMarket(rs.getString(9));
    				result.setHotMarketEndDate(rs.getString(10));
    				result.setSpecialOrder(rs.getString(11));
    				result.setVDCEligible(rs.getString(12));

    				return result;
    			} 
    		});
    	} catch (EmptyResultDataAccessException e) {}

    	return results;
    }
    
    public List<OperationCandyJarResult> getByStyle(String style) throws IOException{
        	
    	String sql = "select SUBSTR(item_image_filename, instr(item_image_filename,'/',-1) + 1, REGEXP_INSTR(item_image_filename,'[?_]',1) - instr(item_image_filename,'/',-1) - 1) as eCode, item_style as Style, item_name as SKU, item_bar_code as UPC, supplier_item_barcode" 
    			+ " as sUPC, description as Description, "
    			+ " ref_field8 as  Presale, REF_DATE_FIELD3_DTTM as Presale_Date, ref_field11 as HotMarket, REF_DATE_FIELD2_DTTM as HotMarket_Date, ref_field7" 
    			+ " as Special_Order, ref_field38 as VDC_Eligible "
    			+ "from item_cbo "
    			+ "left join item_supplier_xref_cbo on item_supplier_xref_cbo.item_barcode = item_cbo.item_bar_code "
    			+ "where item_style = ?"
    			+ "order by eCode, Style, SKU, UPC DESC";

    	List<OperationCandyJarResult> results = new ArrayList<OperationCandyJarResult>();

    	try {
    		results = jdbcTemplate.query(sql,  new Object[] {style}, new RowMapper<OperationCandyJarResult>(){
    			public OperationCandyJarResult mapRow(ResultSet rs, int rowNum)
    					throws SQLException{

    				OperationCandyJarResult result = new OperationCandyJarResult();
    				result.setECode(rs.getString(1));
    				result.setStyle(rs.getString(2));
    				result.setSKU(rs.getString(3));
    				result.setUPC(rs.getString(4));
    				result.setSUPC(rs.getString(5));
    				result.setDescription(rs.getString(6));
    				result.setPresale(rs.getString(7));
    				result.setPresaleEndDate(rs.getString(8));
    				result.setHotMarket(rs.getString(9));
    				result.setHotMarketEndDate(rs.getString(10));
    				result.setSpecialOrder(rs.getString(11));
    				result.setVDCEligible(rs.getString(12));

    				return result;
    			} 
    		});
    	} catch (EmptyResultDataAccessException e) {}

    	return results;
    }
    
    public List<OperationCandyJarResult> getBySku(String sku) throws IOException{
    	
    	String sql = "select SUBSTR(item_image_filename, instr(item_image_filename,'/',-1) + 1, REGEXP_INSTR(item_image_filename,'[?_]',1) - instr(item_image_filename,'/',-1) - 1) as eCode, item_style as Style, item_name as SKU, item_bar_code as UPC, supplier_item_barcode" 
    			+ " as sUPC, description as Description, "
    			+ " ref_field8 as  Presale, REF_DATE_FIELD3_DTTM as Presale_Date, ref_field11 as HotMarket, REF_DATE_FIELD2_DTTM as HotMarket_Date, ref_field7" 
    			+ " as Special_Order, ref_field38 as VDC_Eligible "
    			+ "from item_cbo "
    			+ "left join item_supplier_xref_cbo on item_supplier_xref_cbo.item_barcode = item_cbo.item_bar_code "
    			+ "where item_name = ?"
    			+ "order by eCode, Style, SKU, UPC DESC";
    	
    	List<OperationCandyJarResult> results = new ArrayList<OperationCandyJarResult>();
    	try {
    		results = jdbcTemplate.query(sql,  new Object[] {sku}, new RowMapper<OperationCandyJarResult>(){
    			public OperationCandyJarResult mapRow(ResultSet rs, int rowNum)
    					throws SQLException{

    				OperationCandyJarResult result = new OperationCandyJarResult();
    				result.setECode(rs.getString(1));
    				result.setStyle(rs.getString(2));
    				result.setSKU(rs.getString(3));
    				result.setUPC(rs.getString(4));
    				result.setSUPC(rs.getString(5));
    				result.setDescription(rs.getString(6));
    				result.setPresale(rs.getString(7));
    				result.setPresaleEndDate(rs.getString(8));
    				result.setHotMarket(rs.getString(9));
    				result.setHotMarketEndDate(rs.getString(10));
    				result.setSpecialOrder(rs.getString(11));
    				result.setVDCEligible(rs.getString(12));

    				return result;
    			} 
    		});
    	} catch (EmptyResultDataAccessException e) {}

    	return results;
    }
    
    public List<OperationCandyJarResult> getByUpc(String upc) throws IOException{

    	String sql = "select SUBSTR(item_image_filename, instr(item_image_filename,'/',-1) + 1, REGEXP_INSTR(item_image_filename,'[?_]',1) - instr(item_image_filename,'/',-1) - 1) as eCode, item_style as Style, item_name as SKU, item_bar_code as UPC, supplier_item_barcode" 
    			+ " as sUPC, description as Description, "
    			+ " ref_field8 as  Presale, REF_DATE_FIELD3_DTTM as Presale_Date, ref_field11 as HotMarket, REF_DATE_FIELD2_DTTM as HotMarket_Date, ref_field7" 
    			+ " as Special_Order, ref_field38 as VDC_Eligible "
    			+ "from item_cbo "
    			+ "left join item_supplier_xref_cbo on item_supplier_xref_cbo.item_barcode = item_cbo.item_bar_code "
    			+ "where item_bar_code = ? or supplier_item_barcode = ?"
    			+ "order by eCode, Style, SKU, UPC DESC";

    	List<OperationCandyJarResult> results = new ArrayList<OperationCandyJarResult>();

    	try {
    		results = jdbcTemplate.query(sql,  new Object[] {upc, upc}, new RowMapper<OperationCandyJarResult>(){
    			public OperationCandyJarResult mapRow(ResultSet rs, int rowNum)
    					throws SQLException{

    				OperationCandyJarResult result = new OperationCandyJarResult();
    				result.setECode(rs.getString(1));
    				result.setStyle(rs.getString(2));
    				result.setSKU(rs.getString(3));
    				result.setUPC(rs.getString(4));
    				result.setSUPC(rs.getString(5));
    				result.setDescription(rs.getString(6));
    				result.setPresale(rs.getString(7));
    				result.setPresaleEndDate(rs.getString(8));
    				result.setHotMarket(rs.getString(9));
    				result.setHotMarketEndDate(rs.getString(10));
    				result.setSpecialOrder(rs.getString(11));
    				result.setVDCEligible(rs.getString(12));

    				return result;
    			} 
    		});
    	} catch (EmptyResultDataAccessException e) {}

    	return results;
    }
    
    public String getSku(String upc) {
    	String sql = "select item_name as SKU "
    			+ " from item_cbo "
    			+ " left join item_supplier_xref_cbo on item_supplier_xref_cbo.item_barcode = item_cbo.item_bar_code "
    			+ " where item_bar_code = ? or supplier_item_barcode = ?";
    	String result = jdbcTemplate.queryForObject(sql, new Object[] {upc, upc}, String.class);
    	return result;
    }
    
}
    





