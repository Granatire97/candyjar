package com.dcsg.fulfillment.candyjar;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;

public class OperationCandyJarRepository {
	
    private @Autowired JdbcTemplate jdbcTemplate;

    public ProductCodeMap getByEcode(String eCode) {

        String sql = "select item_image_filename as eCode, item_style as Style, item_name as SKU, item_bar_code as UPC\n" + 
        		"from item_cbo where item_image_filename like = %?%";

        try {
            ProductCodeMap pcm = jdbcTemplate.queryForObject(sql, 
                    new Object[] { eCode }, new ProductCodeMapRowMapper());

            return pcm;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public ProductCodeMap getByStyle(String style) {

        String sql = "select item_image_filename as eCode, item_style as Style, item_name as SKU, item_bar_code as UPC\n" + 
        		"from item_cbo\n" + 
        		"where item_style = ?";

        try {
            ProductCodeMap pcm = jdbcTemplate.queryForObject(sql, 
                    new Object[] { style }, new ProductCodeMapRowMapper());

            return pcm;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public ProductCodeMap getBySku(String sku) {

        String sql = "select item_image_filename as eCode, item_style as Style, item_name as SKU, item_bar_code as UPC\n" + 
        		"from item_cbo\n" + 
        		"where item_name = ?";

        try {
            ProductCodeMap pcm = jdbcTemplate.queryForObject(sql, 
                    new Object[] { sku }, new ProductCodeMapRowMapper());

            return pcm;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public ProductCodeMap getByUpc(String upc) {

        String sql = "select item_image_filename as eCode, item_style as Style, item_name as SKU, item_bar_code as UPC\n" + 
        		"from item_cbo\n" + 
        		"where item_bar_code = ?";

        try {
            ProductCodeMap pcm = jdbcTemplate.queryForObject(sql, 
                    new Object[] { upc }, new ProductCodeMapRowMapper());

            return pcm;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}

class ProductCodeMapRowMapper implements RowMapper<ProductCodeMap> {

    @Override
    public ProductCodeMap mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductCodeMap pcm = new ProductCodeMap();
        String eCode = rs.getString(1);
        String style = rs.getString(2);
        String sku = rs.getString(3);
        String upc = rs.getString(4);
        
        pcm.add(eCode, style, sku, upc);
        return pcm;
    }
}
