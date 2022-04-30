package com.jigpud.snow.service.search;

import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.Story;
import com.jigpud.snow.model.User;
import com.jigpud.snow.repository.attraction.AttractionRepository;
import com.jigpud.snow.repository.search.SearchRepository;
import com.jigpud.snow.repository.story.StoryRepository;
import com.jigpud.snow.repository.user.UserRepository;
import com.jigpud.snow.response.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : jigpud
 */
@Slf4j
@Service
public class SearchServiceImpl implements SearchService {
    private final SearchRepository searchRepository;

    @Autowired
    SearchServiceImpl(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @Override
    public PageData<User> searchUser(String keyWords, long pageSize, long currentPage) {
        return PageData.fromPage(searchRepository.searchUser(keyWords, pageSize, currentPage));
    }

    @Override
    public PageData<Story> searchStory(String keyWords, long pageSize, long currentPage) {
        return PageData.fromPage(searchRepository.searchStory(keyWords, pageSize, currentPage));
    }

    @Override
    public PageData<Attraction> searchAttraction(String keyWords, long pageSize, long currentPage) {
        return PageData.fromPage(searchRepository.searchAttraction(keyWords, pageSize, currentPage));
    }
}
