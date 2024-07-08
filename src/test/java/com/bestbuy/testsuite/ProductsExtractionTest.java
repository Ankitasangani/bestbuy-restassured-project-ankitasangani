package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Extraction Example
 * 21. Extract the limit
 * 22. Extract the total
 * 23. Extract the name of 5th product
 * 24. Extract the names of all the products
 * 25. Extract the productId of all the products
 * 26. Print the size of the data list
 * 27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
 * 28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
 * 29. Get all the categories of 8th products
 * 30. Get categories of the store where product id = 150115
 * 31. Get all the descriptions of all the products
 * 32. Get id of all the all categories of all the products
 * 33. Find the product names Where type = HardGood
 * 34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
 * 35. Find the createdAt for all products whose price < 5.49
 * 36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
 * 37. Find the manufacturer of all the products
 * 38. Find the image of products whose manufacturer is = Energizer
 * 39. Find the createdAt for all categories products whose price > 5.99
 * 40. Find the uri of all the products
 */
public class ProductsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    // 21) Extract the limit
    @Test
    public void extractLimit() {
        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    // 22) Extract the total
    @Test
    public void extractTotal() {
        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    // 23) Extract the name of 5th product
    @Test
    public void extractProductName() {
        String name = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th product is : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    // 24) Extract the names of all the products
    @Test
    public void extractAllProductsName() {
        List<String> allProductsName = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all products: " + allProductsName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 25) Extract the productId of all the products
    @Test
    public void extractAllProductsId() {
        List<Integer> productId = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Id of all products are: " + productId);
        System.out.println("------------------End of Test---------------------------");
    }

    // 26) Print the size of the data list
    @Test
    public void SizeOfData() {
        List<String> data = response.extract().path("data");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of data is: " + data.size());
        System.out.println("------------------End of Test---------------------------");
    }

    // 27) Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void getValue() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the product where product name = Energizer - MAX Batteries AA (4-Pack) is: " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    // 28)Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void getModel() {
        List<String> model = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack) is: " + model);
        System.out.println("------------------End of Test---------------------------");
    }

    // 29) Get all the categories of 8th products
    @Test
    public void getAllCategories() {
        List<HashMap<String, ?>> categories = response.extract().path("data[7].categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all the categories of 8th products are: " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 30) Get categories of the store where product id = 150115
    @Test
    public void getCategories() {
        List<HashMap<String, ?>> categories = response.extract().path("data.findAll{it.id == 150115}.categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories of the store where product id = 150115 is: " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 31) Get all the descriptions of all the products
    @Test
    public void getDescriptions() {
        List<Object> description = response.extract().path("data.description");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the descriptions of all the products are: " + description);
        System.out.println("------------------End of Test---------------------------");
    }

    // 32) Get id of the all categories of all the products
    @Test
    public void findIdOfAllCategories() {
        List<?> id = response.extract().path("data.categories.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The id of the all categories of all the products are:" + id);
        System.out.println("------------------End of Test---------------------------");
    }

    // 33) Find the product names Where type = HardGood
    @Test
    public void findProductName() {
        List<?> name = response.extract().path("data.findAll{it.type == 'HardGood'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The product names Where type = HardGood is:" + name);
        System.out.println("------------------End of Test---------------------------");
    }

    // 34) Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void findTotalCategories() {
        List<?> totalCategories = response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack) is:" + totalCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 35) Find the createdAt for all products whose price < 5.49
    @Test
    public void findCreatedAt() {
        List<String> createdAt = response.extract().path("data.findAll{it.price < 5.49}.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all products whose price < 5.49 is:" + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    // 36) Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void findName() {
        List<String> name = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all categories Where product name = Energizer - MAX Batteries AA (4-Pack) is:" + name);
        System.out.println("------------------End of Test---------------------------");
    }

    // 37) Find the manufacturer of all the products
    @Test
    public void findManufacturer() {
        List<String> manufacturer = response.extract().path("data.manufacturer");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The manufacturer of all the products are:" + manufacturer);
        System.out.println("------------------End of Test---------------------------");
    }

    // 38) Find the image of products whose manufacturer is = Energizer
    @Test
    public void findImageOfProducts() {
        List<String> image = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The image of products whose manufacturer is = Energizer are:" + image);
        System.out.println("------------------End of Test---------------------------");
    }

    // 39) Find the createdAt for all categories products whose price > 5.99
    @Test
    public void findCreatedAtOfProducts() {
        List<String> createdAt = response.extract().path("data.findAll{it.price > 5.99}.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The the createdAt for all categories products whose price > 5.99 are:" + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    // 40) Find the url of all the products
    @Test
    public void findUrl() {
        List<String> url = response.extract().path("data.url");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The url of all the products are:" + url);
        System.out.println("------------------End of Test---------------------------");
    }
}
