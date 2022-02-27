package com.jigpud.snow.util.rolespermissions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : jigpud
 */
public class RolesPermissionsUtil {
    public static String append(String source, String target) {
        if (target == null || target.isEmpty()) {
            return source;
        }
        if (source != null && !source.isEmpty() && !source.endsWith(",")) {
            return source + "," + target;
        } else {
            return target;
        }
    }

    public static List<String> parse(String source) {
        if (source == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(source.split(","))
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.toList());
    }
}
