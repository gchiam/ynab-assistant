package me.chiam.gordon.ynabassistant;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import me.chiam.gordon.ynabassistant.health.SimpleHealthCheck;
import me.chiam.gordon.ynabassistant.resources.HelloWorldResource;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
  public static void main(String[] args) throws Exception {
    new HelloWorldApplication().run(args);
  }

  @Override
  public void run(HelloWorldConfiguration configuration, Environment environment) {
      final HelloWorldResource resource = new HelloWorldResource();

      final SimpleHealthCheck healthCheck = new SimpleHealthCheck();
      environment.healthChecks().register("simple", healthCheck);
      environment.jersey().register(resource);
  }
}
