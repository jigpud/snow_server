package com.jigpud.snow.repository.attractiontag;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.AttractionTagMapper;
import com.jigpud.snow.model.AttractionTag;
import com.jigpud.snow.util.encrypt.Encryptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class AttractionTagRepositoryImpl implements AttractionTagRepository {
    private final AttractionTagMapper attractionTagMapper;

    @Autowired
    AttractionTagRepositoryImpl(AttractionTagMapper attractionTagMapper) {
        this.attractionTagMapper = attractionTagMapper;
    }

    @Override
    public void add(String attractionId, String tag) {
        AttractionTag attractionTag = new AttractionTag();
        attractionTag.setAttractionId(attractionId);
        attractionTag.setTag(tag);
        attractionTag.setTagMd5(Encryptor.md5(tag));
        attractionTagMapper.insertIgnore(attractionTag);
    }

    @Override
    public boolean have(String attractionId, String tag) {
        return attractionTagMapper.exists(attractionAndTagQueryWrapper(attractionId, tag));
    }

    @Override
    public void remove(String attractionId, String tag) {
        attractionTagMapper.delete(attractionAndTagQueryWrapper(attractionId, tag));
    }

    @Override
    public Page<AttractionTag> getTagList(String attractionId, long pageSize, long currentPage) {
        return attractionTagMapper.selectPage(new Page<>(currentPage, pageSize), attractionQueryWrapper(attractionId));
    }

    private QueryWrapper<AttractionTag> attractionAndTagQueryWrapper(String attractionId, String tag) {
        QueryWrapper<AttractionTag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attraction_id", attractionId);
        queryWrapper.eq("tag", tag);
        return queryWrapper;
    }

    private QueryWrapper<AttractionTag> attractionQueryWrapper(String attractionId) {
        QueryWrapper<AttractionTag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attraction_id", attractionId);
        return queryWrapper;
    }
}
