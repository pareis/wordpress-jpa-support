package org.palacehotel.wordpress.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name="Post")
@Table(name="wp_posts")
@NamedQueries ( {
	@NamedQuery( name="videos.all",
			query="SELECT post FROM Post post WHERE post.post_mime_type LIKE '%video%'"),
	@NamedQuery( name="videos.byExcerpt",	
			query="SELECT post FROM Post post WHERE " +
					"post.post_mime_type LIKE '%video%' " +
					"AND post.post_excerpt LIKE ?1")
})
public class Post {

	@Id
	Long ID;

	@Column(length=20, nullable=false, name="post_author")
	Long author;
	@Column(nullable=false)
	Date post_date;
	@Column(nullable=false)
	Date post_date_gmt;
	@Lob
	@Column(nullable=false)
	String post_content;
	@Lob
	@Column(nullable=false, columnDefinition="text")
	String post_title;
	@Column(nullable=false, columnDefinition="text")
	String post_excerpt;
	
	@Column(length=20, nullable=false)
	String post_status;
	@Column(length=20, nullable=false)
	String comment_status;
	@Column(length=20, nullable=false)
	String ping_status;
	@Column(length=20, nullable=false)
	String post_password;
	@Column(length=20, nullable=false)
	String post_name;
	@Lob
	@Column(nullable=false, columnDefinition="text")
	String to_ping;
	@Lob
	@Column(nullable=false, columnDefinition="text")
	String pinged;
	@Column(nullable=false)
	Date post_modified;
	@Column(nullable=false)
	Date post_modified_gmt;
	@Lob
	@Column(nullable=false, columnDefinition="text")
	String post_content_filtered;
	@Column(length=20, nullable=false)
	Long post_parent;
	@Column(length=255, nullable=false)
	String guid;
	@Column(length=11, nullable=false)
	int menu_order;
	@Column(length=20, nullable=false)
	String post_type;
	@Column(length=100, nullable=false)
	String post_mime_type;
	@Column(length=20, nullable=false)
	Long comment_count;
	public Long getID() {
		return ID;
	}
	public Long getAuthor() {
		return author;
	}
	public Date getPost_date() {
		return post_date;
	}
	public Date getPost_date_gmt() {
		return post_date_gmt;
	}
	public String getPost_content() {
		return post_content;
	}
	public String getPost_title() {
		return post_title;
	}
	public String getPost_excerpt() {
		return post_excerpt;
	}
	public String getPost_status() {
		return post_status;
	}
	public String getComment_status() {
		return comment_status;
	}
	public String getPing_status() {
		return ping_status;
	}
	public String getPost_password() {
		return post_password;
	}
	public String getPost_name() {
		return post_name;
	}
	public String getTo_ping() {
		return to_ping;
	}
	public String getPinged() {
		return pinged;
	}
	public Date getPost_modified() {
		return post_modified;
	}
	public Date getPost_modified_gmt() {
		return post_modified_gmt;
	}
	public String getPost_content_filtered() {
		return post_content_filtered;
	}
	public Long getPost_parent() {
		return post_parent;
	}
	public String getGuid() {
		return guid;
	}
	public int getMenu_order() {
		return menu_order;
	}
	public String getPost_type() {
		return post_type;
	}
	public String getPost_mime_type() {
		return post_mime_type;
	}
	public Long getComment_count() {
		return comment_count;
	}
	
	
}
