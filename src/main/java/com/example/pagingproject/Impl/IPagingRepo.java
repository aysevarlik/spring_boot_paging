package com.example.pagingproject.Impl;

import com.example.pagingproject.Entity.PagingEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPagingRepo extends PagingAndSortingRepository<PagingEntity, Long> {
    List<PagingEntity> findAll(Sort sort);
}
