package com.oftekfak.emagazine.repository;

import com.oftekfak.emagazine.entity.UserFollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFollowRepository extends JpaRepository<UserFollowEntity, Long> {
    List<UserFollowEntity> findByMainUser(Long mainUser);

    List<UserFollowEntity> findByFollowedUser(Long followedUser);
}
