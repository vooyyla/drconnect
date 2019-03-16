package com.prophet.drconnect.models;

public class AboutUs {
    private String header;
    private String body;
    private String footer;

    public AboutUs() {}

    public AboutUs(String header, String body, String footer) {
        this.header = header;
        this.body = body;
        this.footer = footer;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public String getFooter() {
        return footer;
    }
}
