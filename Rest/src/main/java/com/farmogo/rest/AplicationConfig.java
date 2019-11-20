package com.farmogo.rest;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("")
public class AplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> classes = new HashSet<>(super.getClasses());
        classes.add(JacksonFeature.class);
        classes.add(com.farmogo.rest.ObjectMapperResolver.class);
        classes.add(com.farmogo.rest.AnimalRS.class);
        classes.add(com.farmogo.rest.AnimalTypesRS.class);
        classes.add(com.farmogo.rest.FarmRs.class);
        classes.add(com.farmogo.rest.GrantAccesRS.class);
        classes.add(com.farmogo.rest.IncidencesRS.class);
        classes.add(com.farmogo.rest.IncidenceTypeRS.class);
        classes.add(com.farmogo.rest.MarketHistoryRS.class);
        classes.add(com.farmogo.rest.RacesRS.class);
        classes.add(com.farmogo.rest.TestRs.class);
        classes.add(com.farmogo.rest.UserRS.class);

        return classes;
    }


}
