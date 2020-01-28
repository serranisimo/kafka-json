package org.acme.quarkus.sample.fruit.json;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import org.acme.quarkus.sample.fruit.Fruit;

public class FruitDeserializer extends ObjectMapperDeserializer<Fruit> {
    public FruitDeserializer() {
        super(Fruit.class);
    }
}
