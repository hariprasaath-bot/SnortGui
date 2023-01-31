package com.snortmanage.springboot.snortmanage.fetcher;

import com.snortmanage.springboot.snortmanage.config.SnortRuleConfig;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface SnortRuleRepo extends CrudRepository <SnortRuleConfig, Integer> {
    ArrayList<SnortRuleConfig> findBysrcip(String var);

    ArrayList<SnortRuleConfig> findByprotocol(String var);

	//void saveOrUpdate(SnortRuleConfig newRecord);

}

