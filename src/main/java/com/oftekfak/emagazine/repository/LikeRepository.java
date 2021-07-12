package com.oftekfak.emagazine.repository;

import com.oftekfak.emagazine.entity.LikeRelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikeRelEntity, Long> {

    @Query("select l.id from LikeRelEntity l " +
            "where l.postId = :postId and l.active = true")
    List<Long> likeCount(Long postId);

    Optional<LikeRelEntity> findByUserIdAndPostId(Long userId, Long postId);
}
