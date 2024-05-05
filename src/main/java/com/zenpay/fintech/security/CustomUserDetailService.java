package com.zenpay.fintech.security;

import com.zenpay.fintech.model.entity.Roles;
import com.zenpay.fintech.model.entity.Users;
import com.zenpay.fintech.service.FintechService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	FintechService authDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = null;
		Users userEntity = authDAO.getUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("No user Found with userName: " + username));
		try {
			userDetails = new User(username, userEntity.getEncryptPassword(), userEntity.isEnabled(), true, true, true,
					getAuthorities(userEntity.getRoles()));
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return userDetails;
	}

	private Collection<? extends GrantedAuthority> getAuthorities(final Collection<Roles> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
	}

	private List<String> getPrivileges(final Collection<Roles> roles) {
		final List<String> privileges = new ArrayList<String>();
		for (Roles role : roles) {
			privileges.add(role.getName());
		}
		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

}
