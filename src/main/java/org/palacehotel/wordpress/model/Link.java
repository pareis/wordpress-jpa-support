package org.palacehotel.wordpress.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name="Link")
@Table(name="wp_links")
@NamedQueries ( {
	@NamedQuery( name="links.all",
			query="SELECT link FROM Link link")
})
public class Link {
	@Id
	Long link_id;
	
	@Column(length=255)
	String link_url, link_name, link_image, link_target, link_description, link_rel,
		link_rss;
	@Column(length=20)
	String link_visible;
	
	long link_owner;
	int link_rating;
	
	@Column(nullable=false, columnDefinition="mediumtext")
	String link_notes;

	public Long getLink_id() {
		return link_id;
	}

	public String getLink_url() {
		return link_url;
	}

	public String getLink_name() {
		return link_name;
	}

	public String getLink_image() {
		return link_image;
	}

	public String getLink_target() {
		return link_target;
	}

	public String getLink_description() {
		return link_description;
	}

	public String getLink_rel() {
		return link_rel;
	}

	public String getLink_rss() {
		return link_rss;
	}

	public String getLink_visible() {
		return link_visible;
	}

	public long getLink_owner() {
		return link_owner;
	}

	public int getLink_rating() {
		return link_rating;
	}

	public String getLink_notes() {
		return link_notes;
	}
}
