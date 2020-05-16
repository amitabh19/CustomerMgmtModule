package com.capstore.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.app.dao.DAO;
import com.capstore.app.dao.DAOImpl;
import com.capstore.app.models.ProductFeedback;

@Service
@Transactional
public class ServiceImpl implements FeedbackService{
	
	@Autowired
	DAO dao;
	public ServiceImpl(){
		dao=new DAOImpl();
		}
	@Override
	public boolean createFeedback(ProductFeedback pf) {
		return dao.createFeedback(pf);
	}
	@Override
	public ProductFeedback getFeedbackByProductId(int productId) {
		return dao.getFeedbackByProductId(productId);
	}
}
