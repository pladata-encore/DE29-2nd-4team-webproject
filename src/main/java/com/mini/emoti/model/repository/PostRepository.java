package com.mini.emoti.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mini.emoti.model.entity.PostEntity;


public interface PostRepository extends JpaRepository<PostEntity, Long>{
// public PostEntity findByCreatedDateAndUserUserId(LocalDateTime createDate, Long userId);

// @Transactional
// public void deleteByPostId(Long postId);

@Transactional
@Modifying
@Query(value = "delete from post p where p.post_id = :postId", nativeQuery = true)
public void deleteByPostId(@Param("postId") Long postId);







} 