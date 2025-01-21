package com.ecommerce.productservicedecember.inheritanceTypes.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "jt_instructors")
@PrimaryKeyJoinColumn(name = "user_id")
public class Instructor extends User{
    private String module;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
