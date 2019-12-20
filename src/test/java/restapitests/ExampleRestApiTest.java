package restapitests;

import helpers.RequestSpecificationHelper;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.json.JSONException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static helpers.Config.*;
import static helpers.TestDataProvider.*;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.POST;

public class ExampleRestApiTest {

    private RequestSpecificationHelper requestSpecificationHelper = new RequestSpecificationHelper();

    @DataProvider(name = "testData")
    public static Object[][] testDataTable() {
        return new Object[][]{
                {"TC001", POST, JSON, getCreateDataServiceBaseUrl(), CREATE_USER_JSON, 200, null, null, null},
                {"TC002", GET, JSON, getListUsersServiceBaseUrl(), null, 200, GET_LIST_OF_USERS_JSON, null, null},
                {"TC003", POST, JSON, getSingleUserServiceBaseUrl(), null, 200, null, "data.email", "janet.weaver@reqres.in"},
                {"TC004", GET, JSON, getSingleUserServiceBaseUrl(), null, 200, GET_SINGLE_USER_JSON, null, null},
        };
    }

    @Test(dataProvider = "testData")
    public void testCaseExecutor(String testCaseID, Method requestMethod, ContentType contentType,
                                 String endpointPath, String requestBodyResourceName,
                                 int responseStatusCode, String responseBodyJsonResourceName,
                                 String jsonKey, String hasItems) throws IOException, JSONException, InterruptedException {
        requestSpecificationHelper.buildHttpRequest(
                testCaseID, requestMethod, contentType, endpointPath, requestBodyResourceName,
                responseStatusCode, responseBodyJsonResourceName, jsonKey, hasItems);
    }
}
