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
 * 1. Extract the limit
 * 2. Extract the total
 * 3. Extract the name of 5th store
 * 4. Extract the names of all the store
 * 5. Extract the storeId of all the store
 * 6. Print the size of the data list
 * 7. Get all the value of the store where store name = St Cloud
 * 8. Get the address of the store where store name = Rochester
 * 9. Get all the services of 8th store
 * 10. Get storeservices of the store where service name = Windows Store
 * 11. Get all the storeId of all the store
 * 12. Get id of all the store
 * 13. Find the store names Where state = ND
 * 14. Find the Total number of services for the store where store name = Rochester
 * 15. Find the createdAt for all services whose name = “Windows Store”
 * 16. Find the name of all services Where store name = “Fargo”
 * 17. Find the zip of all the store
 * 18. Find the zip of store name = Roseville
 * 19. Find the storeservices details of the service name = Magnolia Home Theater
 * 20. Find the lat of all the stores
 */
public class StoresExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    // 1) Extract the limit
    @Test
    public void extractLimit() {
        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    // 2) Extract the total
    @Test
    public void extractTotal() {
        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    // 3) Extract the name of 5th store
    @Test
    public void extractStoreName() {
        String name = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th store is : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    // 4) Extract the names of all the store
    @Test
    public void extractAllStoreName() {
        List<String> allStoreName = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all stores: " + allStoreName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 5) Extract the storeId of all the store
    @Test
    public void extractAllStoreId() {
        List<Integer> allStoreId = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Id of all stores is: " + allStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    // 6) Print the size of the data list
    @Test
    public void SizeOfData() {
        List<String> data = response.extract().path("data");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of data is: " + data.size());
        System.out.println("------------------End of Test---------------------------");
    }

    // 7) Get all the value of the store where store name = St Cloud
    @Test
    public void getValue() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values of store where store name = St Cloud is: " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    // 8) Get the address of the store where store name = Rochester
    @Test
    public void getAddress() {
        List<String> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The address of store where store name = Rochester is: " + address);
        System.out.println("------------------End of Test---------------------------");
    }

    // 9) Get all the services of 8th store
    @Test
    public void getAllServices() {
        List<HashMap<String, ?>> services = response.extract().path("data[7].services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services of store is: " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    // 10) Get storeservices of the store where service name = Windows Store
    @Test
    public void getStoreServices() {
        List<HashMap<String, ?>> storeservices = response.extract().path("data.services*.findAll{it.name=='Windows Store'}.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices of store where service name = Windows Store is : " + storeservices);
        System.out.println("------------------End of Test---------------------------");
    }

    // 11) Get all the storeId of all the store
    @Test
    public void getAllStoreId() {
        List<Object> allStoreId = response.extract().path("data.services.storeservices.storeId");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The StoreId of all stores is: " + allStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    // 12) Get id of all the store
    @Test
    public void getIdOfAllStore() {
        List<Integer> allStoreId = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Id of all stores is: " + allStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    // 13) Find the store names Where state = ND
    @Test
    public void findStoreName() {
        List<?> name = response.extract().path("data.findAll{it.state == 'ND'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store name where State = ND is: " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    // 14) Find the Total number of services for the store where store name = Rochester
    @Test
    public void findTotalServices() {
        List<?> totalServices = response.extract().path("data.findAll{it.name == 'Rochester'}.services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of services for the store where store name = Rochester is:" + totalServices);
        System.out.println("------------------End of Test---------------------------");
    }

    // 15) Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void findCreatedAt() {
        List<String> createdAt = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.storeservices.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all services whose name = Windows Store is:" + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    // 16) Find the name of all services Where store name = “Fargo”
    @Test
    public void findName() {
        List<String> name = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all services Where store name = Fargo is:" + name);
        System.out.println("------------------End of Test---------------------------");
    }

    // 17) Find the zip of all the store
    @Test
    public void findZip() {
        List<String> zip = response.extract().path("data.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of all the store is:" + zip);
        System.out.println("------------------End of Test---------------------------");
    }

    // 18) Find the zip of store name = Roseville
    @Test
    public void findZipOfStore() {
        List<String> zip = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of store name = Roseville is:" + zip);
        System.out.println("------------------End of Test---------------------------");
    }

    // 19) Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void findStoreServices() {
        List<String> storeservices = response.extract().path("data.services*.find{it.name == 'Magnolia Home Theater'}.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The the storeservices details of the service name = Magnolia Home Theater is:" + storeservices);
        System.out.println("------------------End of Test---------------------------");
    }

    // 20) Find the lat of all the stores
    @Test
    public void findLat() {
        List<String> data = response.extract().path("data.lat");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The lat of all the store is:" + data);
        System.out.println("------------------End of Test---------------------------");
    }

}
