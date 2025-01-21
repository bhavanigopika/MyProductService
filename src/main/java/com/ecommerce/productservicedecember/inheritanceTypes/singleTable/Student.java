package com.ecommerce.productservicedecember.inheritanceTypes.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "st_students")
@DiscriminatorValue(value ="1")
public class Student extends User {
    private Double psp;

    public Double getPsp() {
        return psp;
    }

    public void setPsp(Double psp) {
        this.psp = psp;
    }
}
