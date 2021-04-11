package com.oftekfak.emagazine.repository;

import com.oftekfak.emagazine.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
