package com.oftekfak.emagazine.repository;

import com.oftekfak.emagazine.entity.LikeRelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<LikeRelEntity, Long> {

    @Query("select l.id from LikeRelEntity l where l.postId=:postId")
    List<Long> likeCount(Long postId);
}
