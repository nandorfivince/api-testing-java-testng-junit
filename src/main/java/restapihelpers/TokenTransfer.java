package restapihelpers;

import java.io.IOException;

public class TokenTransfer {

    private LoginOkHTTP loginOkHTTP = new LoginOkHTTP();

    public String getToken(String url, String username, String password) throws IOException {
        return loginOkHTTP.getSiteToken(url, username, password);
    }

}
