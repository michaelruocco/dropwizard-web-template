package uk.co.mruoc.health;

import com.codahale.metrics.health.HealthCheck;

public class CustomerTableHealthCheck extends HealthCheck {

    private final Database database;

    public CustomerTableHealthCheck(Database database) {
        this.database = database;
    }

    @Override
    protected Result check() throws Exception {
        if (database.customerTableExists())
            return Result.healthy();
        return Result.unhealthy("database does not contain customer table");
    }

}
