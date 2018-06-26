package com.retailbank.creditcardservice;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureStubRunner(ids = "com.retailbank:creditcheckservice:+:stubs:8080", workOffline = true)//boots wiremock and imports stubs into it
public class CreditcardserviceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldGrantApplicationWhenCreditScoreIsHigh() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/credit-card-applications")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"citizenNumber\": 1234, \"cardType\":\"GOLD\"}"))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().json("{\"status\":\"GRANTED\"}"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

}



