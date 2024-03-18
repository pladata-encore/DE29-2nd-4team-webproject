package com.mini.emoti.model.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini.emoti.model.entity.PostEntity;

import java.util.List;


public interface PostRepository extends JpaRepository<PostEntity, Long>{
// public PostEntity findByCreatedDateAndUserUserId(LocalDateTime createDate, Long userId);

} 