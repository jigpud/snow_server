package com.jigpud.snow.service.favorite;

import com.jigpud.snow.model.StoryFavorite;
import com.jigpud.snow.repository.storyfavorite.StoryFavoriteRepository;
import com.jigpud.snow.response.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : jigpud
 */
@Slf4j
@Service
public class FavoriteServiceImpl implements FavoriteService {
    private final StoryFavoriteRepository storyFavoriteRepository;

    @Autowired
    FavoriteServiceImpl(StoryFavoriteRepository storyFavoriteRepository) {
        this.storyFavoriteRepository = storyFavoriteRepository;
    }

    @Override
    public long storyFavorites(String userid) {
        return storyFavoriteRepository.favorites(userid);
    }

    @Override
    public void favoriteStory(String storyId, String userid) {
        storyFavoriteRepository.add(storyId, userid);
    }

    @Override
    public void unFavoriteStory(String storyId, String userid) {
        storyFavoriteRepository.remove(storyId, userid);
    }

    @Override
    public boolean haveFavoriteStory(String storyId, String userid) {
        return storyFavoriteRepository.have(storyId, userid);
    }

    @Override
    public PageData<String> storyFavoriteList(String userid, long pageSize, long currentPage) {
        return PageData.fromPage(storyFavoriteRepository.favoriteList(userid, pageSize, currentPage), StoryFavorite::getStoryId);
    }
}
