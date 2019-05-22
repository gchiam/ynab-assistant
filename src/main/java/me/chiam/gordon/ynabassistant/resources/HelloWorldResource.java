package me.chiam.gordon.ynabassistant.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/fulfillment")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    public HelloWorldResource() {
    }

    @GET
    @Timed
    public String say() {
        return "Hello World!";
    }
}
