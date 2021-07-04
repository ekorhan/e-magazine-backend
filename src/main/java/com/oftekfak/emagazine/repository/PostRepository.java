package com.oftekfak.emagazine.repository;

import com.oftekfak.emagazine.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Query("select p from PostEntity p where p.userId=:userId order by p.createdAt desc")
    List<PostEntity> findByUserIdOrderByCreatedAtDesc(Long userId);

    @Query("select p from PostEntity p where p.userId in (:ids) order by p.createdAt desc")
    List<PostEntity> findAllByUserIdOrderByCreatedAtDesc(List<Long> ids);
}
