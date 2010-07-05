package org.palacehotel.jpa.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JPAUtils {
	
	@SuppressWarnings("unchecked")
	public static <T> T findUnique(EntityManager em, Class<T> clazz, String queryName, Object... params) {
		Query query = em.createNamedQuery(queryName);
		if (params!=null)
			for (int i=0; i<params.length; i++)
				query.setParameter(i+1, params[i]);
		List<T> results = query.getResultList();
		
		if (results.size()>0)
			return results.get(0);
		else return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> find(EntityManager em, Class<T> clazz, String queryName, Object... params) {
		Query query = em.createNamedQuery(queryName);
		if (params!=null)
			for (int i=0; i<params.length; i++)
				query.setParameter(i+1, params[i]);
		return query.getResultList();
	}
}
