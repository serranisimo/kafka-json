package org.acme.quarkus.sample.fruit;

import io.smallrye.reactive.messaging.annotations.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/fruits")
public class FruitPriceResource {

    Logger LOG = LoggerFactory.getLogger(FruitPriceResource.class);

    @Inject
    @Channel("fruit-out")
    Publisher<Fruit> fruits;

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.APPLICATION_JSON)
    public Publisher<Fruit> stream() {
        LOG.info("JAX-RS Reactive resource - Publisher :{}", fruits);
        return fruits;
    }
}
