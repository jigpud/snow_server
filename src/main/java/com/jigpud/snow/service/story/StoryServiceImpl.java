package com.jigpud.snow.service.story;

import com.jigpud.snow.model.Story;
import com.jigpud.snow.repository.story.StoryRepository;
import com.jigpud.snow.util.response.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : jigpud
 */
@Slf4j
@Service
public class StoryServiceImpl implements StoryService {
    private final StoryRepository storyRepository;

    @Autowired
    StoryServiceImpl(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Override
    public Story getStory(String storyId) {
        return storyRepository.getStory(storyId);
    }

    @Override
    public PageData<Story> getUserStoryList(String authorId, long pageCount, long page) {
        return PageData.fromPage(storyRepository.getUserStoryList(authorId, pageCount, page));
    }

    @Override
    public PageData<Story> getUserMomentsStoryList(String authorId, long pageCount, long page) {
        return null;
    }

    @Override
    public void releaseStory(Story story) {
        storyRepository.addStory(story);
    }
}
