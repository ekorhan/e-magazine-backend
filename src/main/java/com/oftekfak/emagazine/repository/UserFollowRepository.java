package com.oftekfak.emagazine.repository;

import com.oftekfak.emagazine.entity.UserFollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFollowRepository extends JpaRepository<UserFollowEntity, Long> {
    List<UserFollowEntity> findByMainUser(Long mainUser);

    List<UserFollowEntity> findByFollowedUser(Long followedUser);
}
