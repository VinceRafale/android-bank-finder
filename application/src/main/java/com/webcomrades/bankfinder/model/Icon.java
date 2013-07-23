package com.webcomrades.bankfinder.model;

/**
 * User: josomers
 * Date: 10/06/13
 * Time: 14:10
 */

public class Icon {

    private String url;
    private String fileName;

    public Icon() {

    }

    public Icon(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public String getFileName() {
        return fileName;
    }

}
