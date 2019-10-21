package com.example;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactFolder;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@PactFolder("contractfiles")
public class PactTestV2 {
    @Rule
    public PactProviderRule provider = new PactProviderRule("RandomIntService", "localhost", 8123, PactSpecVersion.V2,this);

    @Pact(consumer = "PactTestV2")
    public RequestResponsePact createPact(PactDslWithProvider builder) {

        DslPart body = new PactDslJsonBody()
                .integerType("randomint",222)
                .asBody();

        return builder
                .given("No special state needed")
                .uponReceiving("request for a random integer")
                .path("/cgi-bin/randomint.cgi")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(body)
                .toPact();
    }
    @Test
    @PactVerification()
    public void doTest() {
        String endPoint=provider.getUrl()+"/cgi-bin/randomint.cgi";
        RandomIntConsumer randomIntConsumer=new RandomIntConsumer(endPoint);
        randomIntConsumer.callGetRandomInt();
        assertTrue(randomIntConsumer.getRandomInt() >= 0);
    }
}
