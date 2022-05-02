package com.jigpud.snow.response;

import com.jigpud.snow.model.AttractionPicture;
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
public class AttractionPictureResponse {
    private String attractionId;
    private String uploader;
    private String uploaderAvatar;
    private String uploaderNickname;
    private String url;

    public static AttractionPictureResponse create(AttractionPicture attractionPicture, UserService userService) {
        User uploader = userService.getUserByUserid(attractionPicture.getUploaderId());
        AttractionPictureResponse attractionPictureResponse = new AttractionPictureResponse();
        attractionPictureResponse.setAttractionId(attractionPicture.getAttractionId());
        attractionPictureResponse.setUploader(uploader.getUserid());
        attractionPictureResponse.setUploaderAvatar(uploader.getAvatar());
        attractionPictureResponse.setUploaderNickname(uploader.getNickname());
        attractionPictureResponse.setUrl(attractionPicture.getPicture());
        return attractionPictureResponse;
    }
}
