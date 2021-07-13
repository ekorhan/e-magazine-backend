package com.oftekfak.emagazine.repository;

import com.oftekfak.emagazine.entity.UserFollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFollowRepository extends JpaRepository<UserFollowEntity, Long> {
    List<UserFollowEntity> findByMainUser(Long mainUser);

    List<UserFollowEntity> findByFollowedUser(Long followedUser);

    Optional<UserFollowEntity> findByMainUserAndFollowedUserAndActive(Long mainUser, Long followedUser, boolean isActive);
}
