package com.bondarenko.memoryanalyzer.repository;

import com.bondarenko.memoryanalyzer.entity.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
    @Override
    Page<Data> findAll(Pageable pageable);
}
