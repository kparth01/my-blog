package com.sb.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sb.myblog.model.blogentry.BlogEntryBean;

@Repository("blogAppRepository")
public interface IBlogAppRepository extends JpaRepository<BlogEntryBean, Long> {

}
