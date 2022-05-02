package com.jigpud.snow.repository;

import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.User;
import com.jigpud.snow.repository.recommend.RecommendRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author : jigpud
 */
@Slf4j
@SpringBootTest
public class RecommendRepositoryTest {
    private final RecommendRepository recommendRepository;

    @Autowired
    RecommendRepositoryTest(RecommendRepository recommendRepository) {
        this.recommendRepository = recommendRepository;
    }

    @Test
    public void testGetHotAttractionList() {
        List<Attraction> hotAttractionList = recommendRepository.getHotAttractionList(5, 1).getRecords();
        log.debug("hotAttractionList: {}", hotAttractionList);
    }

    @Test
    public void testGetRecommendAttractionList() {
        List<Attraction> recommendAttractionList =
                recommendRepository.getRecommendAttractionList(10, 1).getRecords();
        log.debug("recommendAttractionList: {}", recommendAttractionList);
    }

    @Test
    public void testGetRecommendUserList() {
        List<User> recommendUserList = recommendRepository.getRecommendUserList("", 10, 1).getRecords();
        log.debug("recommendUserList: {}", recommendUserList);
    }
}
