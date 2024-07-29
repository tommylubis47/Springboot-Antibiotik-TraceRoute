
package com.WebApp.RequestAntibiotik.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import com.WebApp.RequestAntibiotik.Entity.UserApp;
import com.WebApp.RequestAntibiotik.Repository.UserAppRepository;


@Component
public class CustomUserDetailsService implements UserDetailsService  {
    
    	@Autowired
	private UserAppRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserApp user = userRepo.findByEmail(username);
		System.out.println("------------------ user di database ---------------");
		System.out.println(user);
		System.out.println("---------------------------------------------------");
		
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		} else {
			return new CustomUser(user);
		}

	}
        
}
