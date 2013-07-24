package com.webcomrades.bankfinder.controller;

import com.webcomrades.bankfinder.model.Brand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: josomers
 * Date: 10/06/13
 * Time: 14:10
 */

public class BrandManager {

    private Map<String, Brand> brands;

    public BrandManager() {
        this.brands = new HashMap<String, Brand>();
    }

    public BrandManager(List<Brand> brands) {
        this.brands = convertListToMap(brands);
    }

    public Map<String, Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = convertListToMap(brands);
    }

    public Brand getBrandById(String brandId) {
        return brands.get(brandId);
    }

    private Map<String, Brand> convertListToMap(List<Brand> brands) {
        Map<String, Brand> newBrands = new HashMap<String, Brand>();
        for (Brand brand : brands) {
            newBrands.put(brand.getId(), brand);
        }
        return newBrands;
    }

}
