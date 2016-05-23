import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by johnnym on 5/23/16.
 */
public class ConsumerTest {

    private static final int MOCK_PORT = 1234;
    @Rule
    public PactProviderRule provider = new PactProviderRule("PetShop", "localhost", MOCK_PORT, this);
    private int petId = 1;

    @Pact(provider = "PetShop", consumer = "PetBuyer")
    public PactFragment configurationFragment(PactDslWithProvider builder) {
        return builder
                .given("a pet store with pets")
                .uponReceiving("retrieve a pet from the shop")

                    .path("/v2/pet/" + petId )
                    .method("GET")
                    .headers(MapUtils.putAll(new HashedMap(), new String[]{"Accept","application/xml, text/xml, application/xhtml+xml"}))
                .willRespondWith()
                    .status(200)
                    .headers(MapUtils.putAll(new HashedMap(), new String[]{"Content-Type", "application/xml"}))
                    .body("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Pet><category><id>1</id><name>string</name></category><id>1</id><name>doggie</name><photoUrls><photoUrl>string</photoUrl></photoUrls><status>available</status><tags><tag><id>0</id><name>string</name></tag></tags></Pet>")
        .toFragment();
    }

    @PactVerification("PetShop")
    @Test
    public void main() throws Exception {
        Consumer petConsumer = new Consumer();
        System.out.println("--- TEST ---");
        petConsumer.getPet("http://localhost:" + MOCK_PORT +"/v2/pet/" + petId);
        System.out.println("--- TEST ---");
    }

}