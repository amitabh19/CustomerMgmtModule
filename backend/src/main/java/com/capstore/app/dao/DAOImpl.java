package com.example.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capstore.app.models.ProductFeedback;

@Repository
public class DAOImpl implements DAO {
	
	@Autowired
	EntityManager em;
	
	@Override
	public boolean createFeedback(ProductFeedback pf) {
		em.persist(pf);
		return true;
	}

	@Override
	public ProductFeedback getFeedbackByProductId(int productId) {
		try {
			//ProductFeedback pf = em.find(ProductFeedback.class, product_id);
			String query1 = "select p from ProductFeedback p where p.productId=:productId";
			TypedQuery<ProductFeedback> q1 = em.createQuery(query1, ProductFeedback.class);
			q1.setParameter("productId", productId);
			ProductFeedback pf = q1.getSingleResult();
			//System.out.println(pf);
			return pf;
			
		} catch (Exception e) {
			return null;
		}
	}

}
