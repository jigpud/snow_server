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
public class UpdateFoodRequest {
    private String foodId;
    private String name;
    private String description;
    private List<String> pictures;
}
