package com.snortmanage.springboot.snortmanage.fetcher;

import com.snortmanage.springboot.snortmanage.config.rule;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface SnortRuleRepo extends CrudRepository <rule, Integer> {
    ArrayList<rule> findBysrcip(String var);

    ArrayList<rule> findByprotocol(String var);

	//void saveOrUpdate(SnortRuleConfig newRecord);

}

