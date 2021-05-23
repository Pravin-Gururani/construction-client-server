package com.construction.payload.response;
import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private String name;
	private List<String> roles;
	private Boolean isVerified;
	private Boolean isEnabled;
	

	public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles, String name, Boolean isVerified,Boolean isEnabled) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.name=name;
		this.isVerified=isVerified;
		this.isEnabled=isEnabled;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}
	
	

	public void setUsername(String username) {
		this.username = username;
	}
	
	

	public String getName() {
		return name;
	}

	public void setPhone(String name) {
		this.name = name;
	}

	public List<String> getRoles() {
		return roles;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}
	
}