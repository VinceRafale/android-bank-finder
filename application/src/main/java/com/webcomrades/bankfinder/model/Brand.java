package com.webcomrades.bankfinder.model;

import java.util.UUID;

/**
 * User: josomers
 * Date: 10/06/13
 * Time: 14:10
 */

public class Brand {

    private String id;
    private String name;
    private Icon icon;

    public Brand() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Icon getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return name;
    }

}
