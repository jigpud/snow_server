package com.jigpud.snow.service.attraction;

import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.AttractionPicture;
import com.jigpud.snow.model.AttractionTag;
import com.jigpud.snow.model.Food;
import com.jigpud.snow.repository.attraction.AttractionRepository;
import com.jigpud.snow.repository.attractionfood.AttractionFoodRepository;
import com.jigpud.snow.repository.attractionpicture.AttractionPictureRepository;
import com.jigpud.snow.repository.attractionscore.AttractionScoreRepository;
import com.jigpud.snow.repository.attractiontag.AttractionTagRepository;
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
public class AttractionServiceImpl implements AttractionService {
    private final AttractionRepository attractionRepository;
    private final AttractionScoreRepository attractionScoreRepository;
    private final AttractionPictureRepository attractionPictureRepository;
    private final AttractionTagRepository attractionTagRepository;
    private final AttractionFoodRepository attractionFoodRepository;

    @Autowired
    AttractionServiceImpl(
            AttractionRepository attractionRepository,
            AttractionScoreRepository attractionScoreRepository,
            AttractionPictureRepository attractionPictureRepository,
            AttractionTagRepository attractionTagRepository,
            AttractionFoodRepository attractionFoodRepository
    ) {
        this.attractionRepository = attractionRepository;
        this.attractionScoreRepository = attractionScoreRepository;
        this.attractionPictureRepository = attractionPictureRepository;
        this.attractionTagRepository = attractionTagRepository;
        this.attractionFoodRepository  = attractionFoodRepository;
    }

    @Override
    public void addAttraction(Attraction attractionId) {
        attractionRepository.add(attractionId);
    }

    @Override
    public void deleteAttraction(String attractionId) {
        attractionRepository.remove(attractionId);
    }

    @Override
    public boolean haveAttraction(String attractionId) {
        return attractionRepository.have(attractionId);
    }

    @Override
    public void updateAttraction(Attraction attraction) {
        attractionRepository.update(attraction);
    }

    @Override
    public Attraction getAttraction(String attractionId) {
        return attractionRepository.getAttraction(attractionId);
    }

    @Override
    public PageData<Attraction> attractionsNameLike(String name, long pageSize, long currentPage) {
        return PageData.fromPage(attractionRepository.attractionsNameLike(name, pageSize, currentPage));
    }

    @Override
    public PageData<Attraction> getAttractionList(long pageSize, long currentPage) {
        return PageData.fromPage(attractionRepository.getAttractionList(pageSize, currentPage));
    }

    @Override
    public void score(String attractionId, String userid, int score) {
        attractionScoreRepository.add(attractionId, userid, score);
    }

    @Override
    public long scoreCount(String attractionId) {
        return attractionScoreRepository.scoreCount(attractionId);
    }

    @Override
    public float getScore(String attractionId) {
        return attractionScoreRepository.getAverageScore(attractionId);
    }

    @Override
    public int getUserScore(String attractionId, String userid) {
        if (attractionScoreRepository.have(attractionId, userid)) {
            return attractionScoreRepository.get(attractionId, userid).getScore();
        } else {
            return 0;
        }
    }

    @Override
    public boolean haveScored(String attractionId, String userid) {
        return attractionScoreRepository.have(attractionId, userid);
    }

    @Override
    public void addPicture(String attractionId, String userid, String picture) {
        AttractionPicture attractionPicture = new AttractionPicture();
        attractionPicture.setAttractionId(attractionId);
        attractionPicture.setUploaderId(userid);
        attractionPicture.setPicture(picture);
        attractionPicture.setPictureMd5(Encryptor.md5(picture));
        attractionPictureRepository.add(attractionPicture);
    }

    @Override
    public void deletePicture(String attractionId, String userid, String picture) {
        attractionPictureRepository.remove(attractionId, userid, picture);
    }

    @Override
    public void deletePicture(String attractionId, String picture) {
        attractionPictureRepository.remove(attractionId, picture);
    }

    @Override
    public PageData<AttractionPicture> getPictureList(String attractionId, long pageSize, long currentPage) {
        return PageData.fromPage(attractionPictureRepository.getPictureList(attractionId, pageSize, currentPage));
    }

    @Override
    public boolean havePicture(String attractionId, String picture) {
        return attractionPictureRepository.have(attractionId, picture);
    }

    @Override
    public void addTag(String attractionId, String tag) {
        attractionTagRepository.add(attractionId, tag);
    }

    @Override
    public void deleteTag(String attractionId, String tag) {
        attractionTagRepository.remove(attractionId, tag);
    }

    @Override
    public PageData<AttractionTag> getTagList(String attractionId, long pageSize, long currentPage) {
        return PageData.fromPage(attractionTagRepository.getTagList(attractionId, pageSize, currentPage));
    }

    @Override
    public boolean haveTag(String attractionId, String tag) {
        return attractionTagRepository.have(attractionId, tag);
    }

    @Override
    public void addFood(String attractionId, String foodId) {
        attractionFoodRepository.add(attractionId, foodId);
    }

    @Override
    public boolean haveFood(String attractionId, String foodId) {
        return attractionFoodRepository.have(attractionId, foodId);
    }

    @Override
    public void deleteFood(String attractionId, String foodId) {
        attractionFoodRepository.remove(attractionId, foodId);
    }

    @Override
    public PageData<Food> getFoodList(String attractionId, long pageSize, long currentPage) {
        return PageData.fromPage(attractionFoodRepository.getFoodList(attractionId, pageSize, currentPage));
    }
}
