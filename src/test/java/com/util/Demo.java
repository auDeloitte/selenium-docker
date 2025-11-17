package com.util;

// import com.pages.vendorportal.model.VendorPortalTestData;

public class Demo {

    public static void main(String[] args) throws Exception {
        System.setProperty("browser", "firefox");
        Configuration.initialize();
        System.out.println(Configuration.get("selenium.grid.hubHost"));
    }

}
