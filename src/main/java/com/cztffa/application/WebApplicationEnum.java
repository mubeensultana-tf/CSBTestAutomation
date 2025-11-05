package com.cztffa.application;

import java.util.function.Supplier;

public enum WebApplicationEnum {

    CHROME(ChromeWebApplication::new),
    FIREFOX(FirefoxWebApplication::new);

    private Supplier<WebApplication> instantiator;

    protected WebApplication getInstance() {
        return instantiator.get();
    }

    WebApplicationEnum(Supplier<WebApplication> instantiator) {
        this.instantiator = instantiator;
    }
}
