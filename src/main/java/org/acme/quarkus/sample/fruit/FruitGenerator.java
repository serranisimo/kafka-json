package org.acme.quarkus.sample.fruit;

import io.quarkus.kafka.client.serialization.JsonbSerializer;
import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class FruitGenerator {

    Logger LOG = LoggerFactory.getLogger(FruitGenerator.class);
    Random random = new Random();

    @Outgoing("generated-fruit")
    public Flowable<Fruit> generate() {
        return Flowable.interval(5, TimeUnit.SECONDS).map(tick -> {
            int nextInt = random.nextInt(100);
            LOG.info("----------------FruitGenerator-------------------------");
            LOG.info("----------------"+nextInt+"-------------------------");
            Fruit fruit = new Fruit("Fruit ".concat(String.valueOf(nextInt)),nextInt);
            return fruit;
        });
    }
}
