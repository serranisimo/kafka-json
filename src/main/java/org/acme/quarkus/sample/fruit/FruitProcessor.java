package org.acme.quarkus.sample.fruit;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

/**
 * A bean consuming data from the "fruit-in" Kafka topic and applying some price conversion.
 * The result is pushed to the "fruit-out" stream.
 */
@ApplicationScoped
public class FruitProcessor  {
    Logger LOG = LoggerFactory.getLogger(FruitProcessor.class);

    public static final double CONVERSION_RATE = 0.88;

    @Incoming("fruit-in")
    @Outgoing("fruit-out")
    @Broadcast
    public Fruit process(Fruit fruit) {
        LOG.info("----------------FruitProcessor-------------------------");
        LOG.info("----------------"+fruit.getName()+": "+fruit.getPrice()+"----------------");
        double newPrice = fruit.getPrice() * CONVERSION_RATE;
        fruit.setPrice((int) newPrice);
        LOG.info("----------------"+fruit.getName()+": "+fruit.getPrice()+"----------------");
        return fruit;
    }
}
