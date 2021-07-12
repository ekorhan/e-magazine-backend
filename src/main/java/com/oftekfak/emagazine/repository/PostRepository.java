package com.oftekfak.emagazine.repository;

import com.oftekfak.emagazine.entity.PostEntity;
import com.oftekfak.emagazine.model.post.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Query("select p from PostEntity p where p.userId=:userId order by p.createdAt desc")
    List<PostEntity> findByUserIdOrderByCreatedAtDesc(Long userId);

    @Query("select p from PostEntity p where p.userId in (:ids) order by p.createdAt desc")
    List<PostEntity> findAllByUserIdOrderByCreatedAtDesc(List<Long> ids);
/*
    @Query
    List<PostModel> findAllPostFromPostId(List<Long> ids);
*/
    @Query("select new com.oftekfak.emagazine.model.post.PostModel(p) from PostEntity p where p.id=:id ")
    Optional<PostModel> findAllPostFromPostId(@Param("id") Long id);
/*
    @Query
    List<PostModel> findAllPostFromUserId(List<Long> ids);
 */
}
