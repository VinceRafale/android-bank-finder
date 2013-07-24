package com.webcomrades.bankfinder.model;

import com.webcomrades.bankfinder.BankFinder;

import java.util.UUID;

/**
 * User: josomers
 * Date: 10/06/13
 * Time: 14:10
 */

public class Bank {

    private String id;
    private String brandId;
    private String name;
    private String address;

    public Bank(String name, String address, String brandId) {
        this.id = UUID.randomUUID().toString();
        this.brandId = brandId;
        this.name = name;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getBrandId() {
        return brandId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Brand getBrand() {
        return BankFinder.getBrandsManager().getBrandById(brandId);
    }

    @Override
    public String toString() {
        return name + ", " + address;
    }

}
