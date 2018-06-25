package com.retailbank.creditcardservice;

public class ApplyForCeditCardRequest {
  private int citizenNumber;
  private CardType cardType;

  public int getCitizenNumber() {
    return citizenNumber;
  }

  public void setCitizenNumber(int citizenNumber) {
    this.citizenNumber = citizenNumber;
  }

  public CardType getCardType() {
    return cardType;
  }

  public void setCardType(CardType cardType) {
    this.cardType = cardType;
  }
}
