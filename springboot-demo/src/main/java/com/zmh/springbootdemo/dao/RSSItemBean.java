package com.zmh.springbootdemo.dao;

import java.util.Date;
import java.util.Objects;

import com.alibaba.fastjson.annotation.JSONField;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class RSSItemBean {
	@JSONField(name = "_ID")
	private String _id;
	@JSONField(name = "TITLE")
	private String title;
	@JSONField(name = "AUTHOR")
	private String author;
	@JSONField(name = "URI")
	private String uri;
	private String info;
	@JSONField(name = "DESCRIPTION")
	private String description;
	@JSONField(name = "PUBDATE")
	private Date pubDate;
	@JSONField(name = "TYPE")
	private String type;
	private String img;

	public RSSItemBean() {
	}

	public RSSItemBean(String _id, String title, String author, String uri, String info, String description,
			Date pubDate, String type, String img) {
		this._id = _id;
		this.title = title;
		this.author = author;
		this.uri = uri;
		this.info = info;
		this.description = description;
		this.pubDate = pubDate;
		this.type = type;
		this.img = img;
	}

	public String get_id() {
		return this._id;
	}

	public void set_id(String _id) {
		this._id = _id;
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

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
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

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public RSSItemBean _id(String _id) {
		this._id = _id;
		return this;
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

	public RSSItemBean info(String info) {
		this.info = info;
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

	public RSSItemBean img(String img) {
		this.img = img;
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
		return Objects.equals(_id, rSSItemBean._id) && Objects.equals(title, rSSItemBean.title)
				&& Objects.equals(author, rSSItemBean.author) && Objects.equals(uri, rSSItemBean.uri)
				&& Objects.equals(info, rSSItemBean.info) && Objects.equals(description, rSSItemBean.description)
				&& Objects.equals(pubDate, rSSItemBean.pubDate) && Objects.equals(type, rSSItemBean.type)
				&& Objects.equals(img, rSSItemBean.img);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, author, uri);
	}

	@Override
	public String toString() {
		return "{" + " _id='" + get_id() + "'" + ", title='" + getTitle() + "'" + ", author='" + getAuthor() + "'"
				+ ", uri='" + getUri() + "'" + ", info='" + getInfo() + "'" + ", description='" + getDescription() + "'"
				+ ", pubDate='" + getPubDate() + "'" + ", type='" + getType() + "'" + ", img='" + getImg() + "'" + "}";
	}

}
