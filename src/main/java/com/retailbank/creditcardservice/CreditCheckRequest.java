package com.retailbank.creditcardservice;

public class CreditCheckRequest {

  private final int citizenNumber;

  public CreditCheckRequest(int citizenNumber) {
    this.citizenNumber = citizenNumber;
  }

  public int getCitizenNumber() {
    return citizenNumber;
  }
}
