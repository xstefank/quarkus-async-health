package io.xstefank;

import io.smallrye.health.SmallRyeHealth;
import io.smallrye.health.SmallRyeHealthReporter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/async-health")
@ApplicationScoped
public class AsyncHealthResource {

    @Inject
    SmallRyeHealthReporter smallRyeHealthReporter;
    
    @GET
    @Path("/live")
    public void getLivenessAsync() {
        smallRyeHealthReporter.getLivenessAsync()
            .map(SmallRyeHealth::getPayload)
            .convert()
            .toCompletionStage()
            .thenAcceptAsync(System.out::println);
    }
}
