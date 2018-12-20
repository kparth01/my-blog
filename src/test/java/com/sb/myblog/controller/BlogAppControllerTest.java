package com.sb.myblog.controller;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.sb.myblog.model.blogentry.BlogEntryBean;
import com.sb.myblog.service.BlogAppService;

public class BlogAppControllerTest {
	
	private BlogAppService blogAppService = null;
	private BlogAppController blogAppController = null;
	
	@Before
	public void before() {
		blogAppService = mock(BlogAppService.class);
		blogAppController = new BlogAppController();
		ReflectionTestUtils.setField(blogAppController, "blogAppService", blogAppService);
	}
	
	@Test
	public void testCreateBlogEntry() {
		BlogEntryBean blog = new BlogEntryBean();
		blog.setId(1);
		blog.setData("This is create  Blog");
		
		when(blogAppService.createBlogEntry(blog)).thenReturn(blog);
		
		BlogEntryBean result = blogAppController.createBlogEntry(blog);
		
		assertEquals(blog.getId(), result.getId());
	}
	
	@Test
	public void testRetrieveAllBlogEntry() {
		BlogEntryBean blog1 = new BlogEntryBean();
		blog1.setId(1);
		blog1.setData("This is create  Blog");
		
		BlogEntryBean blog2 = new BlogEntryBean();
		blog2.setId(2);
		blog2.setData("This is 2nd  Blog");
		
		BlogEntryBean blog3 = new BlogEntryBean();
		blog3.setId(3);
		blog3.setData("This is 3nd  Blog");
		
		List<BlogEntryBean> blogEntryList = new ArrayList<BlogEntryBean>();
		blogEntryList.add(blog1);
		blogEntryList.add(blog2);
		blogEntryList.add(blog3);
		
		when(blogAppService.retrieveAllBlogEntry()).thenReturn(blogEntryList);
		
		List<BlogEntryBean> result = blogAppController.retrieveAllBlogEntry();
		
		assertEquals(3, result.size());
	}
	
	@Test
	public void testRetrieveBlogEntry() {
		BlogEntryBean blog = new BlogEntryBean();
		blog.setId(1);
		blog.setData("This is create  Blog");
		
		when(blogAppService.getBlogEntryById(1l)).thenReturn(blog);
		
		BlogEntryBean result = blogAppController.retrieveBlogEntry(1l);
		
		assertEquals(blog.getId(), result.getId());
	}
	
	@Test
	public void testUpdateBlogEntry() {
		BlogEntryBean blog = new BlogEntryBean();
		blog.setId(1);
		blog.setData("This is create  Blog");
		
		BlogEntryBean newBlog = new BlogEntryBean();
		newBlog.setId(1);
		newBlog.setData("This is new updated Blog");
		
		when(blogAppService.updateBlogEntry(blog)).thenReturn(newBlog);
		
		BlogEntryBean result = blogAppController.updateBlogEntry(blog);
		
		assertEquals(blog.getId(), result.getId());
		assertTrue(newBlog.getData().equals(result.getData()));
	}
	
	@Test
	public void testDeleteBlogEntry() {
		BlogEntryBean blog = new BlogEntryBean();
		blog.setId(1);
		blog.setData("This is create  Blog");
		
		Mockito.doNothing().when(blogAppService).deleteBlogEntry(1);
		
		ResponseEntity<?> result = blogAppController.deleteBlogEntry(1);
		
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
}
