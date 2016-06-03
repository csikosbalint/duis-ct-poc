import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ConsumerTest {

    private static final int PORT = 8080;
    private static final String CICA = "cica";
    private static final String PATH = "/v1/cica/";
    private int cicaId = 1;

    @Rule
    public PactProviderRule provider = new PactProviderRule("PetShop", "localhost", PORT, this);

    @Pact(consumer = "ConsumerClient")
    public PactFragment configurationFragment(PactDslWithProvider builder) {
        return builder
                .given("State: unlimited pets")
                .uponReceiving("retrieve a cica from the shop")
                    .path(PATH + cicaId)
                    .method("GET")
                .willRespondWith()
                    .status(200)
                    .body(CICA)
        .toFragment();
    }

    @Test
    @PactVerification
    public void runTest() {
        assertEquals(CICA, new ConsumerClient().get("http://localhost:" + PORT + PATH + cicaId));
    }
}