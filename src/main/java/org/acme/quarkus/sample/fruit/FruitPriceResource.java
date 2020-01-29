package org.acme.quarkus.sample.fruit;

import io.smallrye.reactive.messaging.annotations.Channel;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;
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
import java.util.ArrayList;
import java.util.stream.IntStream;

@Path("/fruits")
public class FruitPriceResource {

    Logger LOG = LoggerFactory.getLogger(FruitPriceResource.class);

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.APPLICATION_JSON)
    public Publisher<Fruit> stream() {
        ArrayList<Fruit> list = new ArrayList<Fruit>();
        IntStream.range(0, 100).forEach(
                nbr -> list.add(new Fruit("Fruit".concat(String.valueOf(nbr)), nbr))
        );
        return ReactiveStreams.fromIterable(list).buildRs();
    }
}
