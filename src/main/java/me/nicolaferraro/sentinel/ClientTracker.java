package me.nicolaferraro.sentinel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("singleton")
public class ClientTracker {

    private Map<String, Long> clients = new HashMap<String, Long>();

    private SentinelConfiguration configuration;

    @Autowired
    public ClientTracker(SentinelConfiguration configuration) {
        this.configuration = configuration;
    }

    public synchronized void ping(String client) {
        clients.put(client, System.currentTimeMillis());
    }

    public synchronized Set<String> getExpired() {
        long now = System.currentTimeMillis();
        return clients.entrySet().stream()
                .filter(e -> e.getValue() + configuration.getExpirationDelay() < now)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }


}
