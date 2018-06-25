package com.dcsg.fulfillment.candyjar;

import static org.junit.Assert.assertEquals; 

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(value = "file:./src/test/resources/application.properties")
public class RemoteCommandExecuterTest {
	
	private @Autowired OperationCandyJarConfiguration config;
	
	@Test
	public void testExecuteCommand() throws Exception{
		RemoteCommandExecuter rcm = new RemoteCommandExecuter(config.getUnixUsername(), config.getUnixPassword(), config.getUnixHost(), 22);
		List<String> response = rcm.executeCommand("echo pie");
		assertEquals("pie", response.get(0));

	}
	
}
