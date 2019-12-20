package restapihelpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {

    private static final String REQRES_BASE_URL_ENVVAR_NAME = "REQRES_TEST_BASE_URL";
    private static final String DEFAULT_BASE_URL = "https://reqres.in/api";
    private static Logger LOG = LoggerFactory.getLogger(Config.class);

    private static final String REQRES_BASE_URL = initializeReqresApiBaseURL();

    private static String initializeReqresApiBaseURL() {
        String baseUrlEnvVar = System.getenv(REQRES_BASE_URL_ENVVAR_NAME);
        String baseUrl = baseUrlEnvVar == null ? DEFAULT_BASE_URL : baseUrlEnvVar;
        LOG.info("BASE URL: {}", baseUrl);
        return baseUrl;
    }

    static boolean isSecuredEnvironment() {
        return REQRES_BASE_URL.contains("https");
    }

    public static String getListUsersServiceBaseUrl() {
        return REQRES_BASE_URL + "/users?page=2";
    }

    public static String getSingleUserServiceBaseUrl() {
        return REQRES_BASE_URL + "/users/2";
    }

    public static String getCreateDataServiceBaseUrl() {
        return REQRES_BASE_URL + "/users";
    }

}
