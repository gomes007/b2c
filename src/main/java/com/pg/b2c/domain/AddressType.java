package com.pg.b2c.domain;

public enum AddressType {

    Home("homeAddress"),
    Work("jobAddress");

    private final String label;

    AddressType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
