package com.sandy.shayari.SecondFragment;

public class DataModel {

    private String logo_text, logo_url;

    public DataModel() {
    }

    public DataModel(String logo_text, String logo_url) {
        this.logo_text = logo_text;
        this.logo_url = logo_url;
    }

    public String getLogo_text() {
        return logo_text;
    }

    public void setLogo_text(String logo_text) {
        this.logo_text = logo_text;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }
}
