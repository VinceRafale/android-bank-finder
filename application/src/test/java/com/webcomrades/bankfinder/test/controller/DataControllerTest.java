package com.webcomrades.bankfinder.test.controller;

import com.google.common.collect.ImmutableMap;
import com.webcomrades.bankfinder.BankFinderGlobals;
import com.webcomrades.bankfinder.R;
import com.webcomrades.bankfinder.controller.DataController;
import com.webcomrades.bankfinder.model.Bank;
import com.webcomrades.bankfinder.model.Brand;
import com.webcomrades.bankfinder.test.datafetcher.FileDataFetcher;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

@RunWith(RobolectricTestRunner.class)
public class DataControllerTest {

    @Test
    public void testDataController() {
        ImmutableMap<String, Integer> rawResourceIds = ImmutableMap.of(BankFinderGlobals.PATH_BANK, R.raw.bank,
                BankFinderGlobals.PATH_BRAND, R.raw.brand);

        DataController dataController = new DataController(
                BankFinderGlobals.PATH_BANK,
                BankFinderGlobals.PATH_BRAND,
                new FileDataFetcher(Robolectric.getShadowApplication().getApplicationContext(), rawResourceIds));

        try {
            List<Brand> brands = dataController.getBrands();
            assertEquals(4, brands.size());

            Brand brand = brands.get(0);
            assertEquals("0c2fc1ae-ca11-4fdc-9e62-757ec60147a7", brand.getId());
            assertEquals("BNP Paribas Fortis", brand.getName());

            List<Bank> banks = dataController.getBanks();
            assertEquals(45, banks.size());

            Bank bank = banks.get(2);
            assertEquals("407ecde9-5406-4e4e-ba5f-f182a3de0e9d", bank.getId());
            assertEquals("0c2fc1ae-ca11-4fdc-9e62-757ec60147a7", bank.getBrandId());
            assertEquals("Kantoor Antwerpen-Gerechtshof", bank.getName());
            assertEquals("Amerikalei 1, 2000 Antwerpen", bank.getAddress());

        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

}
