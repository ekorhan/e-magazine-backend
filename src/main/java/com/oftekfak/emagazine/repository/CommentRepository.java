package com.oftekfak.emagazine.repository;

import com.oftekfak.emagazine.entity.CommentRelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface CommentRepository extends JpaRepository<CommentRelEntity, Long> {
    LinkedList<CommentRelEntity> findByPostIdAndActiveOrderByCreatedAtDesc(Long postId, boolean isActive);
}
