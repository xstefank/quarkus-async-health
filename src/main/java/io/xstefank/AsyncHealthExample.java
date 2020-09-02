package io.xstefank;

import io.smallrye.health.api.AsyncHealthCheck;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
@Liveness
public class AsyncHealthExample implements AsyncHealthCheck {
    
    @Override
    public Uni<HealthCheckResponse> call() {
        return Uni.createFrom().completionStage(CompletableFuture.supplyAsync(() -> HealthCheckResponse.up("async-example")));
    }
}
