package com.oftekfak.emagazine.repository;

import com.oftekfak.emagazine.entity.LikeRelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeRelEntity, Long> {

}
