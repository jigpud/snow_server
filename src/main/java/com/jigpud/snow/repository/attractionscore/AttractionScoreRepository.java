package com.jigpud.snow.repository.attractionscore;

import com.jigpud.snow.model.AttractionScore;

/**
 * @author : jigpud
 */
public interface AttractionScoreRepository {
    void add(String attractionId, String userid, int score);

    AttractionScore get(String attractionId, String userid);

    void remove(String attractionId, String userid);

    boolean have(String attractionId, String userid);

    long scoreCount(String attractionId);

    float getAttractionAverageScore(String attractionId);
}
