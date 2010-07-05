package org.palacehotel.wordpress.ngg.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name="Picture")
@Table(name="wp_ngg_pictures")
@NamedQueries ( {
	@NamedQuery( name="pictures.all",
			query="SELECT picture FROM Picture picture"),
	@NamedQuery( name="pictures.byGallery",
			query="SELECT picture FROM Picture picture WHERE picture.gallery.name=?1")
})
public class Picture {

	@Id
	Long pid;
	
	Long post_id;
	String filename;
	Date imagedate;
	@Column(columnDefinition="tinyint")
	boolean exclude;
	long sortorder;
	
	@Lob
	String meta_data;
	
	@Column(columnDefinition="mediumtext")
	String description, alttext;
	
	@ManyToOne
	@JoinColumn(name="galleryid")
	Gallery gallery;

	public Long getPid() {
		return pid;
	}

	public Long getPost_id() {
		return post_id;
	}

	public String getFilename() {
		return filename;
	}

	public Date getImagedate() {
		return imagedate;
	}

	public boolean isExclude() {
		return exclude;
	}

	public long getSortorder() {
		return sortorder;
	}

	public String getMeta_data() {
		return meta_data;
	}

	public String getDescription() {
		return description;
	}

	public String getAlttext() {
		return alttext;
	}

	public Gallery getGallery() {
		return gallery;
	}
}
