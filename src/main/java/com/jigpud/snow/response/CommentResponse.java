package com.jigpud.snow.response;

import com.jigpud.snow.model.Comment;
import com.jigpud.snow.model.User;
import com.jigpud.snow.service.like.LikeService;
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
public class CommentResponse {
    private String commentId;
    private String storyId;
    private String authorId;
    private String authorNickname;
    private String authorAvatar;
    private String content;
    private Long likes;
    private Boolean liked;
    private String replyTo;
    private Long commentTime;

    public static CommentResponse create(Comment comment, String self, UserService userService, LikeService likeService) {
        User author = userService.getUserByUserid(comment.getAuthorId());
        String commentId = comment.getCommentId();
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setCommentId(commentId);
        commentResponse.setStoryId(comment.getStoryId());
        commentResponse.setAuthorId(author.getUserid());
        commentResponse.setAuthorNickname(author.getNickname());
        commentResponse.setAuthorAvatar(author.getAvatar());
        commentResponse.setContent(comment.getContent());
        commentResponse.setLikes(likeService.commentLikes(commentId));
        commentResponse.setLiked(likeService.haveLikedComment(commentId, self));
        commentResponse.setReplyTo(comment.getPid());
        commentResponse.setCommentTime(comment.getCommentTime());
        return commentResponse;
    }
}
