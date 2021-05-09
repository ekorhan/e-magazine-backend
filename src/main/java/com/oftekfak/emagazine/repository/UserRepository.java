package com.oftekfak.emagazine.repository;

import com.oftekfak.emagazine.entity.AppUser;
import com.oftekfak.emagazine.model.user.ProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    @Query("SELECT new com.oftekfak.emagazine.model.user.ProfileModel(u) FROM AppUser u WHERE u.id=:userId")
    Optional<ProfileModel> findSimpleProfileInfo(Long userId);
}
