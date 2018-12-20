package com.sb.myblog.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.myblog.exception.BlogEntryNotFoundException;
import com.sb.myblog.model.blogentry.BlogEntryBean;
import com.sb.myblog.repository.IBlogAppRepository;

@Service("blogAppService")
public class BlogAppService {

	private final Logger logger = LoggerFactory.getLogger(BlogAppService.class);
	
	@Autowired
	private IBlogAppRepository blogAppRepository;
	
	public BlogEntryBean createBlogEntry(BlogEntryBean blog) {
		logger.debug("creating a blog entry");
		return this.blogAppRepository.save(blog);
	}
	
	public List<BlogEntryBean> retrieveAllBlogEntry() {
		logger.debug("retrieve all blog entry");
		return this.blogAppRepository.findAll();
	}
	
	public BlogEntryBean getBlogEntryById(long id) {
		logger.debug(String.format("retrieve a blog entry with id[%s]", id));
		return this.blogAppRepository.findById(id)
				.orElseThrow(() -> new BlogEntryNotFoundException("Blog Entry not found with Id:" + id));
	}
	
	public BlogEntryBean updateBlogEntry(BlogEntryBean blog){
		logger.debug(String.format("update a blog entry with id[%s]", blog.getId()));
		
		BlogEntryBean blogDB = getBlogEntryById(blog.getId());
		
		blogDB.setLastUpdateDate(new Date());
		blogDB.setLastUpdateBy(blog.getLastUpdateBy());
		blogDB.setData(blog.getData());
		
		return this.blogAppRepository.save(blogDB);
	}

	public void deleteBlogEntry(long id) {
		logger.debug(String.format("delete a blog entry with id[%s]", id));
		this.blogAppRepository.deleteById(id);
	}
}
