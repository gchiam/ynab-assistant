package me.chiam.gordon.ynabassistant.health;

import com.codahale.metrics.health.HealthCheck;

public class SimpleHealthCheck extends HealthCheck {
    public SimpleHealthCheck() {
    }

    @Override
    protected Result check() {
       return Result.healthy();
    }
}
