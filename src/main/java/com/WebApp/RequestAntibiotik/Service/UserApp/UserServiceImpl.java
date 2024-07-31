
package com.WebApp.RequestAntibiotik.Service.UserApp;


import com.WebApp.RequestAntibiotik.Entity.UserApp;
import com.WebApp.RequestAntibiotik.Repository.UserAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;


@Service
public class UserServiceImpl implements UserService  {
    
    
	@Autowired
	private UserAppRepository userRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserApp saveUser(UserApp user) {

		String password = passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		UserApp newuser = userRepo.save(user);

		return newuser;
	}
        
}
