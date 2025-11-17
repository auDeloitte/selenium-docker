package com.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSight {

    public static void main(String args[]){
        WebDriver driver = new ChromeDriver();
        String baseUrl="https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html";
        driver.get(baseUrl);
    }
}
