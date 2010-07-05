package org.palacehotel.wordpress.ngg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity(name="Album")
@Table(name="wp_ngg_album")
public class Album {

	@Id
	Long id;
	Long previewpic, pageid;
	
	@Column(columnDefinition="mediumtext")
	String albumdesc;
	@Lob
	String sortorder;
	
	String name;
	
}
