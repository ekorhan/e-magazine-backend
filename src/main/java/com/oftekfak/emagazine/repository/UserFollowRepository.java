package com.oftekfak.emagazine.repository;

import com.oftekfak.emagazine.entity.UserFollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFollowRepository extends JpaRepository<UserFollowEntity, Long> {
    List<UserFollowEntity> findByMainUserAndActive(Long mainUser, boolean isActive);

    List<UserFollowEntity> findByFollowedUserAndActive(Long followedUser, boolean isActive);

    Optional<UserFollowEntity> findByMainUserAndFollowedUser(Long mainUser, Long followedUser);
}
