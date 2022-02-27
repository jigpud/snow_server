package com.jigpud.snow.util.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : jigpud
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageData <T> {
    private List<T> records;
    private long pages;
    private long current;
}
