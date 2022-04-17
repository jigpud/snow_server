package com.jigpud.snow.service.search;

import com.jigpud.snow.model.Story;
import com.jigpud.snow.model.User;
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
    private final UserRepository userRepository;
    private final StoryRepository storyRepository;

    @Autowired
    SearchServiceImpl(UserRepository userRepository, StoryRepository storyRepository) {
        this.userRepository = userRepository;
        this.storyRepository = storyRepository;
    }

    @Override
    public PageData<User> searchUser(String keyWords, long pageSize, long currentPage) {
        return PageData.fromPage(userRepository.blurSearch(keyWords, pageSize, currentPage));
    }

    @Override
    public PageData<Story> searchStory(String keyWords, long pageSize, long currentPage) {
        return PageData.fromPage(storyRepository.blurSearch(keyWords, pageSize, currentPage));
    }
}
