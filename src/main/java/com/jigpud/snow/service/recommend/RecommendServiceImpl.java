package com.jigpud.snow.service.recommend;

import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.User;
import com.jigpud.snow.repository.recommend.RecommendRepository;
import com.jigpud.snow.response.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : jigpud
 */
@Slf4j
@Service
public class RecommendServiceImpl implements RecommendService {
    private final RecommendRepository recommendRepository;

    @Autowired
    RecommendServiceImpl(RecommendRepository recommendRepository) {
        this.recommendRepository = recommendRepository;
    }

    @Override
    public List<Attraction> getHotAttractionList(long hotAttractionCount) {
        return recommendRepository.getHotAttractionList(hotAttractionCount);
    }

    @Override
    public PageData<Attraction> getRecommendAttractionList(long pageSize, long currentPage) {
        return PageData.fromPage(recommendRepository.getRecommendAttractionList(pageSize, currentPage));
    }

    @Override
    public PageData<User> getRecommendUserList(String userid, long pageSize, long currentPage) {
        return PageData.fromPage(recommendRepository.getRecommendUserList(userid, pageSize, currentPage));
    }
}
