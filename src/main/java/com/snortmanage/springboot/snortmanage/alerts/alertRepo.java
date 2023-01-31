package com.snortmanage.springboot.snortmanage.alerts;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface alertRepo extends CrudRepository<alertModel,String>{

	List<alertModel> findByProtocol(String var);

	List<alertModel> findByMsg(String var);

	List<alertModel> findByPriority(String var);

	List<alertModel> findByRid(String var);

}
