package restapihelpers;

import io.restassured.specification.RequestSpecification;

import java.io.IOException;

import static restapihelpers.Config.isSecuredEnvironment;
import static io.restassured.RestAssured.given;

public class RequestSpecificationFactory {

    private static final String CAS_TEST_USER = "testuser@company.com";
    private static final String CAS_PASSWORD = "subidubi2020";
    private static final String PROTECTED_SITE_BASE_URL = "https://someproduction-uat.cloud.io";

    private TokenTransfer tokenTransfer = new TokenTransfer();

    public RequestSpecification create() throws IOException {
        if (isSecuredEnvironment()) {
            String token = tokenTransfer.getToken(PROTECTED_SITE_BASE_URL, CAS_TEST_USER, CAS_PASSWORD);
            return given().auth().oauth2(token);
        } else {
            return given().auth().preemptive().basic("testuser", "hulllalllllllaaaaaa");
        }
    }

}
