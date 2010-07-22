package org.palacehotel.wordpress.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.palacehotel.jpa.utils.JPAUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="Test-context.xml")
public class TestLinks {

	@PersistenceContext
	EntityManager em;
	
	@Test
	public void simpleTest() {
		List<Link> links = JPAUtils.find(em, Link.class, "links.all");
		System.out.println("links: " + links);
	}
	
}
