package com.jigpud.snow.service.story;

import com.jigpud.snow.model.Story;
import com.jigpud.snow.repository.story.StoryRepository;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.util.encrypt.Encryptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public PageData<Story> getUserStoryList(String authorId, long pageSize, long currentPage) {
        return PageData.fromPage(storyRepository.getUserStoryList(authorId, pageSize, currentPage));
    }

    @Override
    public PageData<Story> getAttractionStoryList(String attractionId, long pageSize, long currentPage) {
        return PageData.fromPage(storyRepository.getAttractionStoryList(attractionId, pageSize, currentPage));
    }

    @Override
    public String postStory(String title, String content, List<String> pictures, String userid, String attractionId) {
        String storyId = Encryptor.uuid();
        Story story = new Story();
        story.setTitle(title);
        story.setContent(content);
        story.setPictures(pictures);
        story.setAuthorId(userid);
        story.setAttractionId(attractionId);
        story.setReleaseTime(System.currentTimeMillis());
        story.setStoryId(storyId);
        storyRepository.addStory(story);
        return storyId;
    }

    @Override
    public long userStoryCount(String userid) {
        return storyRepository.userStoryCount(userid);
    }

    @Override
    public long attractionStoryCount(String attractionId) {
        return storyRepository.attractionStoryCount(attractionId);
    }
}
