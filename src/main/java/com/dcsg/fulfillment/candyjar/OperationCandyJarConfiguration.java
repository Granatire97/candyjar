package com.dcsg.fulfillment.candyjar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class OperationCandyJarConfiguration {

	@Value("${unix.username}")
    private String unixUsername;
    @Value("${unix.password}")
    private String unixPassword;
    @Value("${unix.host}")
    private String unixHost;
	public String getUnixUsername() {
		return unixUsername;
	}
	public String getUnixHost() {
		return unixHost;
	}
	public String getUnixPassword() {
		return unixPassword;
	}
    
}
