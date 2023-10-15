package com.fms.repository;

import com.fms.domain.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<File, UUID> {
   Page<File> findByUserIdAndType(UUID userId,String type,Pageable pageable);
   List<File> findByUserId(UUID userId);
}
