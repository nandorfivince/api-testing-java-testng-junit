package restapihelpers;

import static java.util.Objects.requireNonNull;

public class GetSingleUserBuilder {

    private final String email;
    private final String firstName;

    private GetSingleUserBuilder(String email, String firstName) {
        this.email = requireNonNull(email);
        this.firstName = requireNonNull(firstName);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String asJson() {
        String jsonTemplate = "{\"data\": {\"id\": 2,\"email\": \"%s\",\"first_name\": \"%s\",\"last_name\": \"Weaver\",\"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg\"}}";
        return String.format(jsonTemplate, email, firstName);
    }

    public static final class Builder {
        private String email;
        private String firstName;

        private Builder() {
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public GetSingleUserBuilder build() {
            return new GetSingleUserBuilder(email, firstName);
        }
    }

}
