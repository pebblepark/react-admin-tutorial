package com.example.demo.domain.embedded;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class Address {
    private String street;
    private String city;
    private String zipcode;

    public static void patch(Address address, Address newAddress) {
        if(address == null || newAddress == null) return;

        if(newAddress.getCity() != null) {
            address.setCity(newAddress.getCity());
        }
        if(newAddress.getStreet() != null) {
            address.setStreet(newAddress.getStreet());
        }
        if(newAddress.getZipcode() != null) {
            address.setZipcode(newAddress.getZipcode());
        }
    }
}
