package com.jigpud.snow.service.like;

import com.jigpud.snow.repository.commentlike.CommentLikeRepository;
import com.jigpud.snow.repository.storylike.StoryLikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : jigpud
 */
@Slf4j
@Service
public class LikeServiceImpl implements LikeService {
    private final StoryLikeRepository storyLikeRepository;
    private final CommentLikeRepository commentLikeRepository;

    @Autowired
    LikeServiceImpl(StoryLikeRepository storyLikeRepository, CommentLikeRepository commentLikeRepository) {
        this.storyLikeRepository = storyLikeRepository;
        this.commentLikeRepository = commentLikeRepository;
    }

    @Override
    public long storyLikes(String storyId) {
        return storyLikeRepository.storyLikes(storyId);
    }

    @Override
    public void likeStory(String storyId, String userid) {
        storyLikeRepository.add(storyId, userid);
    }

    @Override
    public void unlikeStory(String storyId, String userid) {
        storyLikeRepository.remove(storyId, userid);
    }

    @Override
    public boolean haveLikedStory(String storyId, String userid) {
        return storyLikeRepository.have(storyId, userid);
    }

    @Override
    public long commentLikes(String commentId) {
        return commentLikeRepository.commentLikes(commentId);
    }

    @Override
    public void likeComment(String commentId, String userid) {
        commentLikeRepository.add(commentId, userid);
    }

    @Override
    public void unlikeComment(String commentId, String userid) {
        commentLikeRepository.remove(commentId, userid);
    }

    @Override
    public boolean haveLikedComment(String commentId, String userid) {
        return commentLikeRepository.have(commentId, userid);
    }

    @Override
    public long likes(String userid) {
        return storyLikeRepository.userLikes(userid) + commentLikeRepository.userLikes(userid);
    }
}
