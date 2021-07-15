package com.oftekfak.emagazine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_ai_rel")
public class UserAIRel extends BaseEntity {
    private Long userId;
    private Long aiId;
    private Long numberOfTotalShowedPosts;
    private Long numberOfLikes;

    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "ai_id")
    public Long getAiId() {
        return aiId;
    }

    public void setAiId(Long aiId) {
        this.aiId = aiId;
    }

    @Column(name = "number_of_total_showed_posts")
    public Long getNumberOfTotalShowedPosts() {
        return numberOfTotalShowedPosts;
    }

    public void setNumberOfTotalShowedPosts(Long activeTimeMs) {
        this.numberOfTotalShowedPosts = activeTimeMs;
    }

    @Column(name = "number_of_likes")
    public Long getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(Long numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }
}
