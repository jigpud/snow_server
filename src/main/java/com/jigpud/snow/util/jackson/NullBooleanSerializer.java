package com.jigpud.snow.util.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author jigpud
 * 空Boolean使用"false"代替
 */
public class NullBooleanSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeBoolean(false);
        } else {
            gen.writeObject(value);
        }
    }
}
