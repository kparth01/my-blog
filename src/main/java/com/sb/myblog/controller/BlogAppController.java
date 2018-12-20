package com.sb.myblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sb.myblog.model.blogentry.BlogEntryBean;
import com.sb.myblog.service.BlogAppService;

@Api(value="my-blog-app", description="Following services are exposed for managing back-end.",
		consumes="Consumes the JSON Request.", produces="Produces the JSON Response.")
@RestController
@RequestMapping("/myblog")
public class BlogAppController {
	
	@Autowired
	private BlogAppService blogAppService;
	
	@ApiOperation(value="Create new blog Entry")
	@RequestMapping(value="/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public BlogEntryBean createBlogEntry(@RequestBody BlogEntryBean blog) {
		return this.blogAppService.createBlogEntry(blog);
	}
	
	@ApiOperation(value="View all existing blog entries")
	@RequestMapping(value="/viewall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BlogEntryBean> retrieveAllBlogEntry() {
		return this.blogAppService.retrieveAllBlogEntry();
	}
	
	@ApiOperation(value="View specific blog entry")
	@RequestMapping(value="/view/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BlogEntryBean retrieveBlogEntry(@PathVariable long id) {
		return this.blogAppService.getBlogEntryById(id);
	}
	
	@ApiOperation(value="Update blog Entry")
	@RequestMapping(value="/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public BlogEntryBean updateBlogEntry(@RequestBody BlogEntryBean blog) {
		return this.blogAppService.updateBlogEntry(blog);
	}
	
	@ApiOperation(value="Delete blog Entry")
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteBlogEntry(@PathVariable long id) {
		this.blogAppService.deleteBlogEntry(id);
		return ResponseEntity.ok().build();
	}

}
