package provider;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.TargetRequestFilter;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import com.github.restdriver.clientdriver.ClientDriverRule;
import org.apache.http.HttpRequest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.restdriver.clientdriver.RestClientDriver.giveEmptyResponse;
import static com.github.restdriver.clientdriver.RestClientDriver.onRequestTo;

@RunWith(PactRunner.class)
@Provider("myAwesomeService")
@PactFolder("pacts")
public class ContractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContractTest.class);

    // NOTE: this is just an example of embedded service that listens to requests, you should start here real service
    @ClassRule
    public static final ClientDriverRule embeddedService = new ClientDriverRule(8332);

    @BeforeClass
    public static void setUpService() {
        //Run DB, create schema
        //Run service
        //...
    }

    @Before
    public void before() {
        // Rest data
        // Mock dependent service responses
        // ...
        embeddedService.addExpectation(
                onRequestTo("/data"), giveEmptyResponse()
        );
    }

    @State("default")
    public void toDefaultState() {
        // Prepare service before interaction that require "default" state
        // ...
      LOGGER.info("Now service in default state");
    }

    @TargetRequestFilter
    public void exampleRequestFilter(HttpRequest request) {
      LOGGER.info("exampleRequestFilter called: " + request);
    }

    @TestTarget
    public final Target target = new HttpTarget(8332);
}
