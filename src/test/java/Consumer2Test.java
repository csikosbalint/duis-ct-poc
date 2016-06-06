import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import org.apache.http.HttpStatus;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@PactBroker(host = "172.17.0.4", port = "80")
public class Consumer2Test {

    private static final int PORT = 8081;
    private static final String CICA = "cica";
    private static final String PATH = "/v1/cica/";
    private int cicaId = 1;

    @Rule
    public PactProviderRule provider = new PactProviderRule("AnotherPetShop", "localhost", PORT, this);

    @Pact(consumer = "AnotherConsumerClient")
    public PactFragment configurationFragment(PactDslWithProvider builder) {
        return builder
                .given("State: unlimited pets")
                .uponReceiving("retrieve a cica from the shop")
                    .path(PATH + cicaId)
                    .method("GET")
                .willRespondWith()
                    .status(HttpStatus.SC_OK)
                    .body(CICA)
        .toFragment();
    }

    @Test
    @PactVerification
    public void test() {
        assertEquals(CICA, new ConsumerClient().get("http://localhost:" + PORT + PATH + cicaId));
    }
}