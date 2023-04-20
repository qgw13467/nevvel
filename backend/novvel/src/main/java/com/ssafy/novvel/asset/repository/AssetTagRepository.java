package com.ssafy.novvel.asset.repository;

import com.ssafy.novvel.asset.entity.AssetTag;
import com.ssafy.novvel.asset.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetTagRepository extends JpaRepository<AssetTag, Long> {

    @Query("SELECT DISTINCT at FROM AssetTag at WHERE at.tag in :tags")
    Page<AssetTag> findByTagIn(@Param("tags") List<Tag> tags, Pageable pageable);
}
