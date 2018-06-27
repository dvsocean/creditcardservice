package com.retailbank.creditcardservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class CreditCardApplicationsController {

  private final RestTemplate restTemplate;
  private final String creditcheckserviceBaseUrl;

  public CreditCardApplicationsController(RestTemplate restTemplate, @Value("${creditcheckservice.baseUrl}") String creditcheckserviceBaseUrl) {
    this.restTemplate = restTemplate;
    this.creditcheckserviceBaseUrl = creditcheckserviceBaseUrl;
  }

  @PostMapping("/credit-card-applications")
  public ApplyForCreditCardResponse applyForCreditCard(@RequestBody final ApplyForCeditCardRequest applyForCeditCardRequest){
    final int citizenNumber = applyForCeditCardRequest.getCitizenNumber();
    //defined in application.properties file
    final String url = UriComponentsBuilder.fromHttpUrl(creditcheckserviceBaseUrl).path("credit-scores").toUriString();
    final CreditCheckResponse creditCheckResponse = restTemplate.postForObject(url, new CreditCheckRequest(citizenNumber), CreditCheckResponse.class);
    if(creditCheckResponse.getScore() == Score.HIGH && applyForCeditCardRequest.getCardType() == CardType.GOLD){
      return new ApplyForCreditCardResponse(Status.GRANTED);
    }
    throw new RuntimeException("Card and score not yet implemented");
  }
}
