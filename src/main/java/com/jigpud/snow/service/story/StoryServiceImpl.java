package com.jigpud.snow.service.story;

import com.jigpud.snow.model.Story;
import com.jigpud.snow.model.StoryPicture;
import com.jigpud.snow.repository.story.StoryRepository;
import com.jigpud.snow.repository.storypicture.StoryPictureRepository;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.util.encrypt.Encryptor;
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
    private final StoryPictureRepository storyPictureRepository;

    @Autowired
    StoryServiceImpl(StoryRepository storyRepository, StoryPictureRepository storyPictureRepository) {
        this.storyRepository = storyRepository;
        this.storyPictureRepository = storyPictureRepository;
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
    public String postStory(String title, String content, String userid, String attractionId) {
        String storyId = Encryptor.uuid();
        Story story = new Story();
        story.setTitle(title);
        story.setContent(content);
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

    @Override
    public PageData<Story> getMomentsStoryList(String userid, long pageSize, long currentPage) {
        return PageData.fromPage(storyRepository.getMomentsStoryList(userid, pageSize, currentPage));
    }

    @Override
    public void addPicture(String storyId, String userid, String picture) {
        StoryPicture storyPicture = new StoryPicture();
        storyPicture.setStoryId(storyId);
        storyPicture.setUploaderId(userid);
        storyPicture.setPicture(picture);
        storyPicture.setPictureMd5(Encryptor.md5(picture));
        storyPictureRepository.add(storyPicture);
    }

    @Override
    public void deletePicture(String storyId, String userid, String picture) {
        storyPictureRepository.remove(storyId, userid, picture);
    }

    @Override
    public void deletePicture(String storyId, String picture) {
        storyPictureRepository.remove(storyId, picture);
    }

    @Override
    public boolean havePicture(String storyId, String picture) {
        return storyPictureRepository.have(storyId, picture);
    }

    @Override
    public PageData<StoryPicture> getPictureList(String storyId, long pageSize, long currentPage) {
        return PageData.fromPage(storyPictureRepository.getPictureList(storyId, pageSize, currentPage));
    }
}
