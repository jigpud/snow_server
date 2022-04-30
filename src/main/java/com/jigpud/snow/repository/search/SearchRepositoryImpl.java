package com.jigpud.snow.repository.search;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.SearchMapper;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.Story;
import com.jigpud.snow.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class SearchRepositoryImpl implements SearchRepository {
    private final SearchMapper searchMapper;

    @Autowired
    SearchRepositoryImpl(SearchMapper searchMapper) {
        this.searchMapper = searchMapper;
    }

    @Override
    public Page<User> searchUser(String keyWords, long pageSize, long currentPage) {
        return (Page<User>) searchMapper.searchUser(keyWords, new Page<>(currentPage, pageSize));
    }

    @Override
    public Page<Story> searchStory(String keyWords, long pageSize, long currentPage) {
        return (Page<Story>) searchMapper.searchStory(keyWords, new Page<>(currentPage, pageSize));
    }

    @Override
    public Page<Attraction> searchAttraction(String keyWords, long pageSize, long currentPage) {
        return (Page<Attraction>) searchMapper.searchAttraction(keyWords, new Page<>(currentPage, pageSize));
    }
}
