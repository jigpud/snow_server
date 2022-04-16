package com.jigpud.snow.service.favorite;

import com.jigpud.snow.repository.favorite.FavoriteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : jigpud
 */
@Slf4j
@Service
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;

    @Autowired
    FavoriteServiceImpl(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public long favorites(String userid) {
        return favoriteRepository.favorites(userid);
    }

    @Override
    public void favorite(String userid, String storyId) {
        favoriteRepository.add(userid, storyId);
    }

    @Override
    public void unFavorite(String userid, String storyId) {
        favoriteRepository.remove(userid, storyId);
    }

    @Override
    public boolean haveFavorite(String userid, String storyId) {
        return favoriteRepository.have(userid, storyId);
    }
}
