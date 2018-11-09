package com.misterfat.righthand.dao;

import java.util.List;

import com.misterfat.righthand.model.Coder;

public interface CoderDao {

	List<Coder> findAll();

	void insert(Coder coder);

}
