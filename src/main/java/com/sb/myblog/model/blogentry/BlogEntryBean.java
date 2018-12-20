package com.sb.myblog.model.blogentry;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sb.myblog.model.GenericBean;

@Entity(name="blogEntry")
@Table(name = "blog_entry")
public class BlogEntryBean extends GenericBean {
	
	static final long serialVersionUID = 0L;
	
	@NotBlank
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
