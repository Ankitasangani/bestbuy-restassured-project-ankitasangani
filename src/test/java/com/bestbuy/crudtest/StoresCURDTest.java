package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBaseStore;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class StoresCURDTest extends TestBaseStore {
    static String name = "MADE" + TestUtils.getRandomValue();
    static String type = "Furniture" + TestUtils.getRandomValue();
    static String address = "Bullring" + TestUtils.getRandomValue();
    static String address2 = "ground Floor";
    static String city = "Birmingham";
    static String state = "West Midlands";
    static String zip = TestUtils.getRandomValue();
    static double lat = 75.89855;
    static double lng = -26.55651;
    static String hours = "Mon: 9-10; Tue: 9-10; Wed: 9-10; Thurs: 9-10; Fri: 9-10; Sat: 11-6; Sun: 12-5";

    @Test
    public void created() {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .when()
                .body(storePojo)
                .post();
        response.prettyPrint();
        response.then().log().ifValidationFails().statusCode(201);
    }

    @Test
    public void read() {
        Response response = given()
                .when()
                .get();

        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void updated() {
        String name = StoresCURDTest.name + "Updated";
        String type = StoresCURDTest.type + "Updated";
        String address = StoresCURDTest.address + "Updated";
        String address2 = StoresCURDTest.address2;
        String city = StoresCURDTest.city + "Updated";
        String state = StoresCURDTest.state + "Updated";
        String zip = TestUtils.getRandomValue();
        double lat = 75.89855;
        double lng = -26.55651;
        String hours = "Mon: 9-10; Tue: 9-10; Wed: 9-10; Thurs: 9-10; Fri: 9-10; Sat: 8-10; Sun: 10-5";

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .pathParam("id", 4)
                .when()
                .body(storePojo)
                .put("/{id}");
        response.prettyPrint();
        response.then().log().ifValidationFails().statusCode(200);

    }

    @Test
    public void deleted() {
        given().log().ifValidationFails()
                .pathParam("id", 8824)
                .when()
                .delete("/{id}")
                .then().log().ifValidationFails().statusCode(200);
        given()
                .pathParam("id", 8824)
                .when()
                .get("/{id}")
                .then()
                .statusCode(404);
    }
}
