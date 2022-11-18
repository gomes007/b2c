package com.pg.b2c.domain;

public enum PaymentModality {

    MONEY,
    DEBIT_CARD,
    BILL;

    enum CREDIT_CARD{
        VISA,
        MASTERCARD,
        OTHERS
    }

}
