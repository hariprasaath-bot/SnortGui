package com.snortmanage.springboot.snortmanage.usermanager;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface UserRegRepo extends CrudRepository <user,String>{
	user findByusername(String var);

    List<user> findByemail(String search);

    List<user> findByoperatingSystem(String search);
}
