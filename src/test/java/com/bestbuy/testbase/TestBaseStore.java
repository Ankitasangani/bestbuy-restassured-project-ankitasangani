package com.bestbuy.testbase;


import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class TestBaseStore {

    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/stores";
    }
}