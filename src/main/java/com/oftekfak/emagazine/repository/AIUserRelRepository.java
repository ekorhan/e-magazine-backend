package com.oftekfak.emagazine.repository;

import com.oftekfak.emagazine.entity.UserAIRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface AIUserRelRepository extends JpaRepository<UserAIRel, Long> {
    @Query("select uar from UserAIRel uar where uar.userId=:userId " +
            "order by uar.numberOfTotalShowedPosts/uar.numberOfLikes")
    LinkedList<UserAIRel> getMostSuccessfulAI(@Param(value = "userId") Long userId);
}
