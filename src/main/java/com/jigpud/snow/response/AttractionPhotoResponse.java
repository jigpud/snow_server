package com.jigpud.snow.response;

import com.jigpud.snow.model.AttractionPhoto;
import com.jigpud.snow.model.User;
import com.jigpud.snow.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : jigpud
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttractionPhotoResponse {
    private String attractionId;
    private String uploader;
    private String uploaderAvatar;
    private String uploaderNickname;
    private String url;

    public static AttractionPhotoResponse create(AttractionPhoto attractionPhoto, UserService userService) {
        User uploader = userService.getUserByUserid(attractionPhoto.getUploaderId());
        AttractionPhotoResponse attractionPhotoResponse = new AttractionPhotoResponse();
        attractionPhotoResponse.setAttractionId(attractionPhoto.getAttractionId());
        attractionPhotoResponse.setUploader(uploader.getUserid());
        attractionPhotoResponse.setUploaderAvatar(uploader.getAvatar());
        attractionPhotoResponse.setUploaderNickname(uploader.getNickname());
        return attractionPhotoResponse;
    }
}
