
package com.WebApp.RequestAntibiotik.Config;


import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.WebApp.RequestAntibiotik.Entity.UserApp;

public class CustomUser implements UserDetails  {


	private UserApp userApp;

	public CustomUser(UserApp userApp) {
		super();
		this.userApp = userApp;
	}
	
	public void setUserEntity(UserApp userApp) { 
		this.userApp = userApp;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userApp.getRole());

		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		return userApp.getPassword();
	}

	@Override
	public String getUsername() {
		return userApp.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
        
}
