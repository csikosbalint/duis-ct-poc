package io.swagger;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
@RunWith(PactRunner.class)
@Provider("PetShop") // Set up name of tested provider
@PactFolder("src/test/resources") // Point where
public class Swagger2SpringBootTest {

    @TestTarget // Annotation denotes Target that will be used for tests
    public final Target target = new HttpTarget("http","petstore.swagger.io", 8332); // Out-of-the-box implementation of Target (for more information take a look at Test Target section)

}