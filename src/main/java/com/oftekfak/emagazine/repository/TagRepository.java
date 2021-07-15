package com.oftekfak.emagazine.repository;

import com.oftekfak.emagazine.entity.TagEntity;
import com.oftekfak.emagazine.model.post.TagModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
    @Query("select new com.oftekfak.emagazine.model.post.TagModel(t) from TagEntity t where t.active = true")
    List<TagModel> getAllTags();
}
