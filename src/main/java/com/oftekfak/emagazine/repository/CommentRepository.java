package com.oftekfak.emagazine.repository;

import com.oftekfak.emagazine.entity.CommentRelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentRelEntity, Long> {
}
