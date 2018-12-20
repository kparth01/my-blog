package com.sb.myblog.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.sb.myblog.model.blogentry.BlogEntryBean;
import com.sb.myblog.repository.IBlogAppRepository;

public class BlogAppServiceTest {
	
	private IBlogAppRepository blogAppRepository;
	private BlogAppService blogAppService = null;
	
	@Before
	public void Before() {
		blogAppRepository = mock(IBlogAppRepository.class);
		blogAppService = new BlogAppService();
		ReflectionTestUtils.setField(blogAppService, "blogAppRepository", blogAppRepository);
	}
	
	@Test
	public void testCreateBlogEntry() {
		BlogEntryBean blog = new BlogEntryBean();
		blog.setId(1);
		blog.setData("This is create  Blog");
		
		when(blogAppRepository.save(blog)).thenReturn(blog);
		
		BlogEntryBean result = blogAppService.createBlogEntry(blog);
		
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
		
		when(blogAppRepository.findAll()).thenReturn(blogEntryList);
		
		List<BlogEntryBean> result = blogAppService.retrieveAllBlogEntry();
		
		assertEquals(3, result.size());
	}
	
	@Test
	public void testRetrieveBlogEntry() {
		BlogEntryBean blog = new BlogEntryBean();
		blog.setId(1);
		blog.setData("This is create  Blog");

		when(blogAppRepository.findById(1l)).thenReturn(Optional.ofNullable(blog));
		
		BlogEntryBean result = blogAppService.getBlogEntryById(1l);
		
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
		
		when(blogAppRepository.findById(1l)).thenReturn(Optional.ofNullable(blog));
		when(blogAppRepository.save(blog)).thenReturn(newBlog);
		
		BlogEntryBean result = blogAppService.updateBlogEntry(newBlog);
		
		assertEquals(blog.getId(), result.getId());
		assertTrue(newBlog.getData().equals(result.getData()));
	}
	
	@Test
	public void testDeleteBlogEntry() {
		BlogEntryBean blog = new BlogEntryBean();
		blog.setId(1);
		blog.setData("This is create  Blog");
		
		Mockito.doNothing().when(blogAppRepository).deleteById(1l);
		
		blogAppService.deleteBlogEntry(1l);

	}

}
