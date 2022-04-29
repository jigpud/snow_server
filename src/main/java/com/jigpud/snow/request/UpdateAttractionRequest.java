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
public class UpdateAttractionRequest {
    private String attractionId;
    private String name;
    private String description;
    private List<String> tags;
    private String location;
}
