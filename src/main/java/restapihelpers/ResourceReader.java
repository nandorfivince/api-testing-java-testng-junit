package restapihelpers;

import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.Charset;

public class ResourceReader {

    public static String readByName(String resourceName) throws IOException {
        return Resources.toString(Resources.getResource(resourceName), Charset.defaultCharset());
    }

}
