package com.snortmanage.springboot.snortmanage.usermanager;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface UserRegRepo extends CrudRepository <UserModel,String>{
	UserModel findByusername(String var);

    List<UserModel> findByemail(String search);

    List<UserModel> findByoperatingSystem(String search);
}
