package com.oftekfak.emagazine.repository;

import com.oftekfak.emagazine.entity.PostEntity;
import com.oftekfak.emagazine.model.post.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Query("select new com.oftekfak.emagazine.model.post.PostModel(p, u) from PostEntity p " +
            "join AppUser u on p.userId = u.id where p.id=:id ")
    Optional<PostModel> findAllPostFromPostId(@Param("id") Long id);

    @Query("select new com.oftekfak.emagazine.model.post.PostModel(p, u) from PostEntity p " +
            "join AppUser u on p.userId = u.id order by p.createdAt desc")
    List<PostModel> findAllPost();

    @Query("select new com.oftekfak.emagazine.model.post.PostModel(p, u) from PostEntity p " +
            "join AppUser u on p.userId = u.id where p.userId=:userId order by p.createdAt desc")
    LinkedList<PostModel> findPostsFromUserId(@Param("userId") Long userId);

    @Query("select new com.oftekfak.emagazine.model.post.PostModel(p, u) from PostEntity p " +
            "join AppUser u on p.userId=u.id where p.userId in (:ids) order by p.createdAt desc")
    LinkedList<PostModel> findPostsFromUserIds(@Param("ids") List<Long> ids);
}
