package com.oftekfak.emagazine.repository;

import com.oftekfak.emagazine.appuser.AppUser;
import com.oftekfak.emagazine.model.user.ProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    @Query("SELECT new com.oftekfak.emagazine.model.user.ProfileModel(email,firstName,lastName) FROM AppUser WHERE id=:userId")
    Optional<ProfileModel> findProfileInformation(Long userId);
}
