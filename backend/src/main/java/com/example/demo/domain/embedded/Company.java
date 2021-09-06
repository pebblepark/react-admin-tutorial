package com.example.demo.domain.embedded;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class Company {
    @Column(name="company_name")
    private String name;
    private String catchPhrase;
    private String bs;

    public static void patch(Company company, Company newCompany) {
        if(company == null || newCompany == null) return;

        if(newCompany.getName() != null) {
            company.setName(newCompany.getName());
        }
        if(newCompany.getCatchPhrase() != null) {
            company.setCatchPhrase(newCompany.getCatchPhrase());
        }
        if(newCompany.getBs() != null) {
            company.setBs(newCompany.getBs());
        }
    }
}
