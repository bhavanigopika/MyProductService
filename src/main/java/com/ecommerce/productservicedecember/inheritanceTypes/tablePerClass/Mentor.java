package com.ecommerce.productservicedecember.inheritanceTypes.tablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_mentors")

public class Mentor extends User {
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
