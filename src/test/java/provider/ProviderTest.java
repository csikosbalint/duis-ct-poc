package provider;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.TargetRequestFilter;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.apache.http.HttpRequest;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import au.com.dius.pact.provider.junit.Provider;

@RunWith(PactRunner.class)
@Provider("PetShop")
@PactFolder("pacts")
public class ProviderTest {

    @TestTarget
    public final Target target = new HttpTarget(8080);

    @BeforeClass //Method will be run once: before whole contract test suite
    public static void setUpService() {
        //Run DB, create schema
        //Run service
        //...
        provider.Provider.main(new String[]{});
    }

    @State("State: unlimited pets")
    public void toDefaultState() {
        // Prepare service before interaction that require "default" state
        // ...
    }

}