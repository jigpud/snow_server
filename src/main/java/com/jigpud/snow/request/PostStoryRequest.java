package com.jigpud.snow.request;

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
public class PostStoryRequest {
    private String title;
    private String content;
    private List<String> pictures;
    private String attractionId;
}
