package com.example.demo;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.demo.entity.User;

public class CustomUserDetails implements UserDetails{
	private User user;
	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}
	@Override
	public boolean isAccountNonExpired() {
		 return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		 return true;
	}@Override
	public boolean isCredentialsNonExpired() {
		 return true;
	}
	@Override
	public boolean isEnabled() {
		 return true;
	}
	//Phần thêm
	public String getFullName() {
		return user.getFullName();
	}
}
