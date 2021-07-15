package com.oftekfak.emagazine.repository;

import com.oftekfak.emagazine.entity.AIEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AIRepository extends JpaRepository<AIEntity, Long> {

    @Query("select ai.shortCode from AIEntity ai where ai.id = :id")
    Optional<String> findShortCodeById(@Param("id") Long id);
}
