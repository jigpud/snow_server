package com.jigpud.snow.util.jackson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;

/**
 * @author jigpud
 * 自定义Jackson处理空值
 */
public class CustomSerializerProvider extends DefaultSerializerProvider {
    public CustomSerializerProvider() {}

    public CustomSerializerProvider(SerializerProvider src, SerializationConfig config, SerializerFactory jsf) {
        super(src, config, jsf);
    }

    @Override
    public JsonSerializer<Object> findNullValueSerializer(BeanProperty property) throws JsonMappingException {
        JavaType type = property.getType();
        if (type.isArrayType() || type.isCollectionLikeType()) {
            return new NullArraySerializer();
        } else if (type.getRawClass() == Boolean.class) {
            return new NullBooleanSerializer();
        } else if (type.isPrimitive() && type.getRawClass() != Boolean.class) {
            return new NullNumberSerializer();
        } else if (type.getRawClass() == String.class) {
            return new NullStringSerializer();
        }
        return super.findNullValueSerializer(property);
    }

    @Override
    public DefaultSerializerProvider createInstance(SerializationConfig config, SerializerFactory jsf) {
        return new CustomSerializerProvider(this, config, jsf);
    }
}
