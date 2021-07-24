package restapitests;

import restapihelpers.RequestSpecificationHelper;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.json.JSONException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static restapihelpers.Config.*;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.Method.*;
import static restapihelpers.TestDataProvider.*;

public class ExampleRestApiTest {

    private RequestSpecificationHelper requestSpecificationHelper = new RequestSpecificationHelper();

    @DataProvider(name = "testData")
    public static Object[][] testDataTable() {
        return new Object[][]{
                {"TC001", POST, JSON, getCreateDataServiceBaseUrl(), CREATE_USER_JSON, 201, null, null, null},
                {"TC002", GET, JSON, getListUsersServiceBaseUrl(), null, 200, GET_LIST_OF_USERS_JSON, null, null},
                {"TC003", POST, JSON, getRegisterUserBaseUrl(), REGISTER_SUCCESSFUL_USER_JSON, 200, null, "id", "4"},
                {"TC004", POST, JSON, getRegisterUserBaseUrl(), REGISTER_UNSUCCESSFUL_USER_JSON, 400, null, "error", "Missing password"}
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
