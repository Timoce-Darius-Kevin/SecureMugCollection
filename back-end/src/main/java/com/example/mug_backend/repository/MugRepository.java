package com.example.mug_backend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mug_backend.model.Mug;

@Repository
public interface MugRepository extends JpaRepository<Mug, Long> {

}
