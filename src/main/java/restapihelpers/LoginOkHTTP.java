package restapihelpers;

import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Objects;

public class LoginOkHTTP {

    String getSiteToken(String url, String username, String password) throws IOException {
        Client client = new Client();
        ClientResponse entry = client.execute(new Request.Builder().get().url(String.format("%s/resources/apikey", url)).build());
        String loginUrl = entry.html().select("#login a").attr("href");
        ClientResponse casRedirect = client.execute(new Request.Builder().get().url(loginUrl).build());
        String execution = casRedirect.html().select("input[name=execution]").val();
        ClientResponse casLogin = client.execute(new Request.Builder()
                .post(new FormBody.Builder()
                        .add("execution", execution)
                        .add("_eventId", "submit")
                        .add("username", username)
                        .add("password", password)
                        .build())
                .url(casRedirect.url()).build());
        return casLogin.body();
    }

    private interface ClientResponse {
        String body();

        HttpUrl url();

        default Document html() {
            return Jsoup.parse(body());
        }
    }

    private static class Client {

        private final OkHttpClient okHttp = new OkHttpClient.Builder()
                .followRedirects(true)
                .cookieJar(new JavaNetCookieJar(new CookieManager(null, CookiePolicy.ACCEPT_ALL)))
                .build();

        ClientResponse execute(final Request request) throws IOException {
            try (Response response = okHttp.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                HttpUrl url = response.request().url();
                String body = Objects.requireNonNull(response.body()).string();
                return new ClientResponse() {
                    @Override
                    public HttpUrl url() {
                        return url;
                    }

                    @Override
                    public String body() {
                        return body;
                    }
                };
            }
        }
    }

}
