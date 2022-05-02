package com.jigpud.snow.request.admin;

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
    private List<String> pictures;
    private List<String> foods;
    private String location;
}
