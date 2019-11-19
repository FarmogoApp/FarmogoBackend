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
        registerPackage(this.getClass().getClassLoader(), this.getClass().getPackage().getName(),classes);
        return classes;
    }


    private void registerPackage(ClassLoader classLoader, String sourcePackage, Set<Class<?>> resources) {
        String dottedPackage = sourcePackage.replace("[/]", ".");
        String dashPackage = dottedPackage.replace(".", "/");
        BufferedReader br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(dashPackage)));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                if (line.endsWith(".class")) {
                    Class<?> c = classLoader.loadClass(dottedPackage + "." + line.substring(0, line.lastIndexOf('.')));
                    resources.add(c);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error initializing JAX-RS", ex);
        }
    }

}
