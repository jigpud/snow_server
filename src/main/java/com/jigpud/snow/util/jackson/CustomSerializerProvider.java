package com.jigpud.snow.util.jackson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : jigpud
 * 自定义Jackson处理空值
 */
@Slf4j
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
        } else if (isNumberType(type.getRawClass())) {
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

    private boolean isNumberType(Class<?> clazz) {
        return clazz == Float.class ||
                clazz == Double.class ||
                clazz == Integer.class ||
                clazz == Character.class ||
                clazz == Long.class ||
                clazz == Byte.class ||
                (clazz.isPrimitive() && clazz != Boolean.class);
    }
}
