
package com.misterfat.righthand.service;

import java.util.List;

import com.misterfat.righthand.model.Coder;

public interface CoderService {

	List<Coder> findCoders();

	void addCoder(Coder coder);

}
