package com.jigpud.snow.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : jigpud
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UploadTokenResponse {
    private String uploadToken;
    private String key;

    public static UploadTokenResponse create(String uploadToken, String key) {
        UploadTokenResponse uploadTokenResponse = new UploadTokenResponse();
        uploadTokenResponse.setUploadToken(uploadToken);
        uploadTokenResponse.setKey(key);
        return uploadTokenResponse;
    }
}
