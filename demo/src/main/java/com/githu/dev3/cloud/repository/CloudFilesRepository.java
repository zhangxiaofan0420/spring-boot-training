package com.githu.dev3.cloud.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.githu.dev3.cloud.entity.CloudFiles;

public interface CloudFilesRepository extends PagingAndSortingRepository<CloudFiles, Long> {

}
