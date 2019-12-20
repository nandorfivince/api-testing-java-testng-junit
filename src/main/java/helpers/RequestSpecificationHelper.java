package helpers;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.Assert;

import java.io.IOException;

import static helpers.ResourceReader.readByName;

public class RequestSpecificationHelper {

    private RequestSpecificationFactory requestSpecificationFactory = new RequestSpecificationFactory();

    public void buildHttpRequest(String testCaseID, Method requestMethod, ContentType contentType,
                                 String endpointPath, String requestBodyResourceName,
                                 int responseStatusCode, String responseBodyJsonResourceName, String jsonKey, String hasItems) throws IOException, JSONException, InterruptedException {
        System.out.println("Executing test: {" + testCaseID + "}");
        RequestSpecification requestSpecification = requestSpecificationFactory.create();
        if (contentType != null) {
            requestSpecification = requestSpecification.contentType(contentType);
        }
        if (requestBodyResourceName != null) {
            requestSpecification = requestSpecification.body(readByName(requestBodyResourceName));
        }
        Thread.sleep(1250);
        Response response = requestSpecification
                .when()
                .request(requestMethod, endpointPath);
        Assert.assertEquals(response.statusCode(), responseStatusCode);
        if (responseBodyJsonResourceName != null) {
            JSONAssert.assertEquals(readByName(responseBodyJsonResourceName), response.getBody().asString(), true);
        }
        if (jsonKey != null && hasItems != null) {
            Assert.assertTrue(response.body().asString().contains(hasItems));
        }
    }
}
