package com.ssafy.novvel.asset.repository;

import com.ssafy.novvel.asset.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    List<Tag>  findByTagNameIn(List<String> tagNames);
}
