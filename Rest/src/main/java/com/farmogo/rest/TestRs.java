package com.farmogo.rest;

import com.farmogo.services.TestService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RequestScoped
@Path("test")
public class TestRs {

    @Inject
    private TestService testService;

    @GET
    public String test() {
        return testService.test();
    }
}
