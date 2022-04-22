package com.jigpud.snow.repository.attractionscore;

/**
 * @author : jigpud
 */
public interface AttractionScoreRepository {
    void add(String userid, String attractionId, int score);

    void remove(String userid, String attractionId);

    boolean have(String userid, String attractionId);

    long scoreCount(String attractionId);

    float getAttractionAverageScore(String attractionId);
}
