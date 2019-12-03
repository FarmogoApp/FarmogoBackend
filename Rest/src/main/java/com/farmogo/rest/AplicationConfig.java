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
        classes.add(ObjectMapperResolver.class);
        classes.add(AnimalRS.class);
        classes.add(AnimalTypesRS.class);
        classes.add(FarmRs.class);
        classes.add(GrantAccesRS.class);
        classes.add(IncidencesRS.class);
        classes.add(IncidenceTypeRS.class);
        classes.add(MarketHistoryRS.class);
        classes.add(RacesRS.class);
        classes.add(TestRs.class);
        classes.add(UserRS.class);
        classes.add(FirebaseFilter.class);
        return classes;
    }


}
