package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBaseProduct;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest extends TestBaseProduct {
    static String name = "Samsung" + TestUtils.getRandomValue();
    static String type = "Mobile" + TestUtils.getRandomValue();
    static Double price = 900.00;
    static String upc = TestUtils.getRandomValue();
    static Double shipping = 3.99;
    static String description = "IP68 dust and water resistance";
    static String manufacturer = "Samsung";
    static String model = "Samsung";
    static String url = "https://www.samsung.com/uk/smartphones/galaxy-s24/buy/";
    static String image = "https://www.samsung.com/uk/smartphones/galaxy-s24/buy//images/overview/5x-zoom/pro-zoom_endframe__bpmc72f8qwgi_large.jpg";
    static int productId;

    @Test
    public void test001() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .when()
                .body(productPojo)
                .post();
        response.prettyPrint();
        response.then().log().ifValidationFails().statusCode(201);
    }

    @Test
    public void test002() {
        Response response = given()
                .when()
                .get();

        response.then().statusCode(200);

        response.prettyPrint();
    }

    @Test
    public void test003() {
        String name = ProductsCRUDTest.name + "Updated";
        String type = ProductsCRUDTest.type + "Updated";
        Double price = 900.00;
        String upc = TestUtils.getRandomValue();
        Double shipping = 3.99;
        String description = ProductsCRUDTest.description + "Updated";
        String manufacturer = ProductsCRUDTest.manufacturer + "Updated";
        String model = ProductsCRUDTest.model + "Updated";
        String url = ProductsCRUDTest.url + "Updated";
        String image = ProductsCRUDTest.image + "Updated";

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .pathParam("id", 9999679)
                .when()
                .body(productPojo)
                .put("/{id}");
        response.prettyPrint();
        response.then().log().ifValidationFails().statusCode(200);

    }

    @Test
    public void test004() {
        given().log().ifValidationFails()
                .pathParam("id", 9999680)
                .when()
                .delete("/{id}")
                .then().log().ifValidationFails().statusCode(200);
        given()
                .pathParam("id", 9999680)
                .when()
                .get("/{id}")
                .then()
                .statusCode(404);
    }

}
