package com.example;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.runner.RunWith;

@RunWith(PactRunner.class) // Say JUnit to run tests with custom Runner
@Provider("RandomIntService") // Set up name of tested provider
@PactFolder("contractfiles") // Point where to find pacts (See also section Pacts source in documentation)

public class VerifyContract {

    @State("No special state needed")
    public void noSpecialState() {}


    @TestTarget
    public final Target target = new HttpTarget("http", "www2.freefarm.se", 80);

}