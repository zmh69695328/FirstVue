package com.zmh.springbootdemo.dao;

import java.util.Date;
import java.util.Objects;

import com.alibaba.fastjson.annotation.JSONField;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.Data;

@Component
@Data
public class RSSItemBean {
	@JSONField(name = "TITLE")
	private String title;
	@JSONField(name = "AUTHOR")
	private String author;
	@JSONField(name = "URI")
	private String uri;
	@JSONField(name = "LINK")
	private String link;
	@JSONField(name = "DESCRIPTION")
	private String description;
	@JSONField(name = "PUBDATE")
	private Date pubDate;
	@JSONField(name = "TYPE")
	private String type;

	public RSSItemBean() {
	}

	public RSSItemBean(String title, String author, String uri, String link, String description, Date pubDate,
			String type) {
		this.title = title;
		this.author = author;
		this.uri = uri;
		this.link = link;
		this.description = description;
		this.pubDate = pubDate;
		this.type = type;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPubDate() {
		return this.pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RSSItemBean title(String title) {
		this.title = title;
		return this;
	}

	public RSSItemBean author(String author) {
		this.author = author;
		return this;
	}

	public RSSItemBean uri(String uri) {
		this.uri = uri;
		return this;
	}

	public RSSItemBean link(String link) {
		this.link = link;
		return this;
	}

	public RSSItemBean description(String description) {
		this.description = description;
		return this;
	}

	public RSSItemBean pubDate(Date pubDate) {
		this.pubDate = pubDate;
		return this;
	}

	public RSSItemBean type(String type) {
		this.type = type;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof RSSItemBean)) {
			return false;
		}
		RSSItemBean rSSItemBean = (RSSItemBean) o;
		return Objects.equals(title, rSSItemBean.title) && Objects.equals(author, rSSItemBean.author)
				&& Objects.equals(uri, rSSItemBean.uri) && Objects.equals(link, rSSItemBean.link)
				&& Objects.equals(description, rSSItemBean.description) && Objects.equals(pubDate, rSSItemBean.pubDate)
				&& Objects.equals(type, rSSItemBean.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, author, uri, link, description, pubDate, type);
	}

	@Override
	public String toString() {
		return "{" + " title='" + getTitle() + "'" + ", author='" + getAuthor() + "'" + ", uri='" + getUri() + "'"
				+ ", link='" + getLink() + "'" + ", description='" + getDescription() + "'" + ", pubDate='"
				+ getPubDate() + "'" + ", type='" + getType() + "'" + "}";
	}
}
