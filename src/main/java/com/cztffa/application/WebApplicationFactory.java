package com.cztffa.application;

public class WebApplicationFactory {

    public WebApplication getWebApplication(WebApplicationEnum webApplicationType){
        return webApplicationType.getInstance();
    }
}
