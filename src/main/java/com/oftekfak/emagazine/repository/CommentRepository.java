package com.oftekfak.emagazine.repository;

import com.oftekfak.emagazine.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
