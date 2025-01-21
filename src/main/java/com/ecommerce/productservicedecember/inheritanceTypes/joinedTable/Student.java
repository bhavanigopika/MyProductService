package com.ecommerce.productservicedecember.inheritanceTypes.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "jt_students")
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User{
    private Double psp;

    public Double getPsp() {
        return psp;
    }

    public void setPsp(Double psp) {
        this.psp = psp;
    }
}
