package org.acme.quarkus.sample.fruit.json;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;
import org.acme.quarkus.sample.fruit.Fruit;

public class FruitDeserializer extends JsonbDeserializer<Fruit> {
    public FruitDeserializer() {
        super(Fruit.class);
    }
}
