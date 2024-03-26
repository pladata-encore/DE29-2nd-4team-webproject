package com.mini.emoti.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mini.emoti.model.dao.PostDao;
import com.mini.emoti.model.entity.PostEntity;
import com.mini.emoti.model.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostDaoImpl implements PostDao {

    @Autowired
    private PostRepository postRepository;

    @Override
    public void deletePost(Long postId) {
        // TODO Auto-generated method stub
        log.info("" + postId);
        // postRepository.deleteById(postId);
        postRepository.deleteByPostId(postId);

    }

    @Override
    public List<PostEntity> getAllPost() {
        // TODO Auto-generated method stub
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
    }

    @Override
    public void updatePost(PostEntity entity) {
        // TODO Auto-generated method stub
        postRepository.save(entity);
    }

    @Override
    public Long writePost(PostEntity entity) {
        // TODO Auto-generated method stub
        PostEntity savedPost = postRepository.save(entity);
        return savedPost.getPostId();
    }

    @Override
    public PostEntity findById(Long postId) {
        // TODO Auto-generated method stub
        return postRepository.findById(postId).get();
    }

}
