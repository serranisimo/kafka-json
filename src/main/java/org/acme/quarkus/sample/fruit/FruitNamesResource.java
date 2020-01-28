package org.acme.quarkus.sample.fruit;

import io.smallrye.reactive.messaging.annotations.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/fruitnames")
public class FruitNamesResource {
    Logger LOG = LoggerFactory.getLogger(FruitPriceResource.class);

    @Inject
    @Channel("my-data-stream-names")
    Publisher<String> fruitNamesPublisher;

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.APPLICATION_JSON)
    public Publisher<String> stream() {
        LOG.info("JAX-RS Reactive resource - Publisher :{}", fruitNamesPublisher);
        return fruitNamesPublisher;
    }
}
