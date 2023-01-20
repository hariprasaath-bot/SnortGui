package com.snortmanage.springboot.snortmanage.fetcher;

import com.snortmanage.springboot.snortmanage.config.SnortRuleConfig;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SnortRuleRepo extends CrudRepository <SnortRuleConfig, Integer> {
    List<SnortRuleConfig> findBysrcip(String var);

    List<SnortRuleConfig> findByprotocol(String var);

}

