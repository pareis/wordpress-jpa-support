package org.palacehotel.wordpress.ngg.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="Gallery")
@Table(name="wp_ngg_gallery")
@NamedQueries ( {
	@NamedQuery( name="galleries.all",
			query="SELECT gallery FROM Gallery gallery")
})
public class Gallery {

	@Id
	Long gid;
	Long pageid, previewpic, author;
	
	@Column(columnDefinition="mediumtext")
	String path, title, galdesc;
	String name;
	
	@OneToMany(mappedBy="gallery")
	Set<Picture> pictures;

	public Long getGid() {
		return gid;
	}

	public Long getPageid() {
		return pageid;
	}

	public Long getPreviewpic() {
		return previewpic;
	}

	public Long getAuthor() {
		return author;
	}

	public String getPath() {
		return path;
	}

	public String getTitle() {
		return title;
	}

	public String getGaldesc() {
		return galdesc;
	}

	public String getName() {
		return name;
	}

	public Set<Picture> getPictures() {
		return pictures;
	}
}
