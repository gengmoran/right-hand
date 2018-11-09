package com.misterfat.righthand.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misterfat.righthand.dao.CoderDao;
import com.misterfat.righthand.model.Coder;
import com.misterfat.righthand.service.CoderService;

@Service
public class CoderServiceImpl implements CoderService {

	@Autowired
	CoderDao coderDao;

	@Override
	public List<Coder> findCoders() {
		return coderDao.findAll();
	}

	@Override
	public void addCoder(Coder coder) {
		coderDao.insert(coder);
	}

}
