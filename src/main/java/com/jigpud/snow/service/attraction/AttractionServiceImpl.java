package com.jigpud.snow.service.attraction;

import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.AttractionPhoto;
import com.jigpud.snow.repository.attraction.AttractionRepository;
import com.jigpud.snow.repository.attractionphoto.AttractionPhotoRepository;
import com.jigpud.snow.repository.attractionscore.AttractionScoreRepository;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.util.encrypt.Encryptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : jigpud
 */
@Slf4j
@Service
public class AttractionServiceImpl implements AttractionService {
    private final AttractionRepository attractionRepository;
    private final AttractionScoreRepository attractionScoreRepository;
    private final AttractionPhotoRepository attractionPhotoRepository;

    @Autowired
    AttractionServiceImpl(
            AttractionRepository attractionRepository,
            AttractionScoreRepository attractionScoreRepository,
            AttractionPhotoRepository attractionPhotoRepository
    ) {
        this.attractionRepository = attractionRepository;
        this.attractionScoreRepository = attractionScoreRepository;
        this.attractionPhotoRepository = attractionPhotoRepository;
    }

    @Override
    public void addAttraction(Attraction attraction) {
        attractionRepository.add(attraction);
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
    public void score(String userid, String attractionId, int score) {
        attractionScoreRepository.add(userid, attractionId, score);
    }

    @Override
    public long scoreCount(String attractionId) {
        return attractionScoreRepository.scoreCount(attractionId);
    }

    @Override
    public float getScore(String attractionId) {
        return attractionScoreRepository.getAttractionAverageScore(attractionId);
    }

    @Override
    public boolean haveScored(String userid, String attractionId) {
        return attractionScoreRepository.have(userid, attractionId);
    }

    @Override
    public void addPhoto(String uploaderId, String attractionId, String photo) {
        AttractionPhoto attractionPhoto = new AttractionPhoto();
        attractionPhoto.setUploaderId(uploaderId);
        attractionPhoto.setAttractionId(attractionId);
        attractionPhoto.setPhoto(photo);
        attractionPhoto.setPhotoMd5(Encryptor.md5(photo));
        attractionPhotoRepository.add(attractionPhoto);
    }

    @Override
    public void deletePhoto(String uploaderId, String attractionId, String photo) {
        attractionPhotoRepository.remove(uploaderId, attractionId, photo);
    }

    @Override
    public List<String> getAttractionPhotoList(String attractionId) {
        return attractionPhotoRepository.getAttractionPhotoList(attractionId).stream()
                .map(AttractionPhoto::getPhoto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean havePhoto(String attractionId, String photo) {
        return attractionPhotoRepository.have(attractionId, photo);
    }
}
