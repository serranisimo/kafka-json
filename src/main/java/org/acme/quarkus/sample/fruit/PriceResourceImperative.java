package org.acme.quarkus.sample.fruit;

import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

public class PriceResourceImperative {

    /**
     *
     Sometimes, you need to have an imperative way of sending messages.
     For example, if you need to send a message to a stream,
     from inside a REST endpoint, when receiving a POST request.
     In this case, you cannot use @Output because your method has parameters.
     For this, you can use an Emitter.

     */

    @Inject
    @Channel("price-create")
    Emitter<Double> priceEmitter;

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void addPrice(Double price) {
        priceEmitter.send(price);
    }
}
