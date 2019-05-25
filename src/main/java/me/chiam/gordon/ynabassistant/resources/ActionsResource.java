package me.chiam.gordon.ynabassistant.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.actions.api.App;
import me.chiam.gordon.ynabassistant.actions.YnabActionsApp;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Path("/fulfillment")
@Produces(MediaType.APPLICATION_JSON)
public class ActionsResource {
  private final App actionsApp = new YnabActionsApp();
  @Context HttpServletRequest request;

  public ActionsResource() {}

  @GET
  @Timed
  public String say() {
    return "Hello World!";
  }

  @POST
  @Timed
  public String fulfill(String requestBody) throws IOException {
    Map<String, String> headers = getHeadersMap(request);

    try {
      return actionsApp.handleRequest(requestBody, headers).get();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
      return e.getLocalizedMessage();
    }
  }

  private Map<String, String> getHeadersMap(HttpServletRequest request) {
    Map<String, String> map = new HashMap<>();

    Enumeration headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String key = (String) headerNames.nextElement();
      String value = request.getHeader(key);
      map.put(key, value);
    }
    return map;
  }
}
