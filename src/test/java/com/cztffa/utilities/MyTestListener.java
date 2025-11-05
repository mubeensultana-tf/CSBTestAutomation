package com.cztffa.utilities;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Result;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestCase;
import io.cucumber.plugin.event.TestCaseFinished;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyTestListener implements ConcurrentEventListener {
    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
    }

    private void handleTestCaseFinished(TestCaseFinished event) {
        TestCase testCase = event.getTestCase();
        Result result = event.getResult();
        Status status = result.getStatus();
        Throwable error = result.getError();
        String scenarioName = testCase.getName();
        String id = "" + testCase.getUri() + testCase.getLocation();
        log.info("Testcase " + id + " - " + status.name());
        log.info("Test Case scenario {} execution Finished with the status {}", scenarioName, status);
        if(error != null) {
        	log.error("error occured while executing the test case {}", error.getMessage());
        }

    }

}
