package restfulecommerce;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.BeforeClass;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected Playwright playwright;
    protected APIRequestContext request;

    private void createPlaywright() {
        playwright = Playwright.create();
    }

    private void createAPIRequestContext() {
        Dotenv dotenv = Dotenv.load();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(MessageFormat.format("http://{0}:{1}", dotenv.get("HOST"), dotenv.get("PORT")))
                .setExtraHTTPHeaders(headers));
    }

    @BeforeClass
    public void setup() {
        createPlaywright();
        createAPIRequestContext();
    }

    private void closePlaywright() {
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }

    private void disposeAPIRequestContext() {
        if (request != null) {
            request.dispose();
            request = null;
        }
    }
}
