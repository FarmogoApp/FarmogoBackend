package com.farmogo.rest;

import com.farmogo.model.Building;
import com.farmogo.model.Farm;
import com.farmogo.services.DivisionService;
import com.farmogo.model.Division;
import com.farmogo.services.FarmService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@RequestScoped
@Path("farms")
public class FarmRs {
    @Inject
    FarmService farmService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Farm> getAll() {
        return farmService.getAll();
    }


    @GET
    @Path("test")
    public String test(){


        Farm f1 = new Farm();
        f1.setOfficialId("1234");
        f1.setName("farm 1");

        Building b11 = new Building();
        b11.setName("build 1.1");

        Building b12 = new Building();
        b12.setName("build 1.2");

        Division d111 = new Division();
        d111.setName("division 1.1.1");

        Division d112 = new Division();
        d112.setName("division 1.1.2");

        Division d121 = new Division();
        d121.setName("division 1.2.1");

        b11.setDivisions(Arrays.asList(d111,d112));
        b12.setDivisions(Arrays.asList(d121));

        f1.setBuildings(Arrays.asList(b11,b12));

        for (int i = 1; i < 11; i++) {
            f1.setName("Farm " + i);
            f1.setOfficialId("ID " + i);
            farmService.save(f1);
        }


        f1 = new Farm();
        f1.setOfficialId("5678");
        f1.setName("farm 2");

        b11 = new Building();
        b11.setName("build 2.1");

        b12 = new Building();
        b12.setName("build 2.2");

        d111 = new Division();
        d111.setName("division 2.1.1");

        d112 = new Division();
        d112.setName("division 2.1.2");

        d121 = new Division();
        d121.setName("division 2.2.1");

        b11.setDivisions(Arrays.asList(d111,d112));
        b12.setDivisions(Arrays.asList(d121));

        f1.setBuildings(Arrays.asList(b11,b12));

        farmService.save(f1);

        return "ok";
    }
}