package com.dcsg.fulfillment.candyjar;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

@Repository
public class OperationCandyJarRepository {
	
    private @Autowired JdbcTemplate jdbcTemplate;

    public ProductCodeMap getByEcode(String eCode) {
    	
    	System.out.println("howdy");
    	
        String sql = "select item_image_filename as eCode, item_style as Style, item_name as SKU, item_bar_code as UPC " + 
        		"from item_cbo " + 
        		"where item_image_filename like ?";

        try {
            ProductCodeMap pcm = new ProductCodeMap();
            pcm.addAll(jdbcTemplate.query(sql, new Object[] {"%" + eCode + "%"}, new ProductCodeMapRowMapper()));
            
            System.out.println(pcm);
            
            return pcm;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public ProductCodeMap getByStyle(String style) {
    	System.out.println("howdy");
        String sql = "select item_image_filename as eCode, item_style as Style, item_name as SKU, item_bar_code as UPC " + 
        		"from item_cbo " + 
        		"where item_style = ?";

        try {
        	ProductCodeMap pcm = new ProductCodeMap();
            pcm.addAll(jdbcTemplate.query(sql, new Object[] {style}, new ProductCodeMapRowMapper()));
            
            System.out.println(pcm);
            return pcm;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public ProductCodeMap getBySku(String sku) {

        String sql = "select item_image_filename as eCode, item_style as Style, item_name as SKU, item_bar_code as UPC " + 
        		"from item_cbo " + 
        		"where item_name = ?";

        try {
        	ProductCodeMap pcm = new ProductCodeMap();
            pcm.addAll(jdbcTemplate.query(sql, new Object[] {sku}, new ProductCodeMapRowMapper()));

            return pcm;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public ProductCodeMap getByUpc(String upc) {

        String sql = "select item_image_filename as eCode, item_style as Style, item_name as SKU, item_bar_code as UPC " + 
        		"from item_cbo " + 
        		"where item_bar_code = ?";

        try {
        	ProductCodeMap pcm = new ProductCodeMap();
            pcm.addAll(jdbcTemplate.query(sql, new Object[] {upc}, new ProductCodeMapRowMapper()));

            return pcm;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}

class ProductCodeMapRowMapper implements RowMapper<List<String>> {

    @Override
    public List<String> mapRow(ResultSet rs, int rowNum) throws SQLException {
        ArrayList<String> productInfo = new ArrayList<String>();
        String eCode = rs.getString(1).split("/")[6].split("_")[0];
        productInfo.add(eCode);
        productInfo.add(rs.getString(2));
        productInfo.add(rs.getString(3));
        productInfo.add(rs.getString(4));
        
        return productInfo;
    }
}


