package com.retailbank.creditcardservice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CreditCardApplicationsController {

  private final RestTemplate restTemplate;

  public CreditCardApplicationsController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @PostMapping("/credit-card-applications")
  public ApplyForCreditCardResponse applyForCreditCard(final ApplyForCeditCardRequest applyForCeditCardRequest){
    final int citizenNumber = applyForCeditCardRequest.getCitizenNumber();
    final CreditCheckResponse creditCheckResponse = restTemplate.postForObject("http://localhost:8080/credit-scores", new CreditCheckRequest(citizenNumber), CreditCheckResponse.class);
    if(creditCheckResponse.getScore() == Score.HIGH && applyForCeditCardRequest.getCardType() == CardType.GOLD){
      return new ApplyForCreditCardResponse(Status.GRANTED);
    }
    throw new RuntimeException("Card and score not yet implemented");
  }
}
