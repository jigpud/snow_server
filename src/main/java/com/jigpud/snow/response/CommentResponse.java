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
public class CommentResponse {
    private String commentId;
    private String storyId;
    private String authorId;
    private String authorNickname;
    private String content;
    private Long likes;
    private Boolean liked;
}
