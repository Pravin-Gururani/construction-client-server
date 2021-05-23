package com.construction.security.services;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.construction.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String email;

	@JsonIgnore
	private String password;
	
	private String name;

	private Collection<? extends GrantedAuthority> authorities;
	
	private Boolean isVerified;
	
	private Boolean isEnabled;

	public UserDetailsImpl(Long id, String username, String email, String password,String name,
			Collection<? extends GrantedAuthority> authorities, Boolean isVerified,Boolean isEnabled) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.name=name;
		this.authorities = authorities;
		this.isVerified=isVerified;
		this.isEnabled=isEnabled;
	}
	
	public UserDetailsImpl(Long id, String username, String email, String password,String name,
			Collection<? extends GrantedAuthority> authorities,Boolean isEnabled) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.name=name;
		this.authorities = authorities;
		this.isEnabled=isEnabled;
	}

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(),
				user.getName(),
				authorities,
				user.getEmployeeData().isVerified(),
				user.getIsEnabled());
	}
	
	
	
	public static UserDetailsImpl buildUser(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(),
				user.getName(),
				authorities,
				user.getIsEnabled());
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}
	
	public Boolean getIsVerified() {
		return isVerified;
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
		return isEnabled;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}