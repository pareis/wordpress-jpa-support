package org.palacehotel.wordpress.security;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;

@SuppressWarnings("serial")
public class WordpressUserdetails implements UserDetails {

	private long id;
	GrantedAuthority[] authorities;
	private float tzOffset = 0f;
	private boolean daylightSaving = true;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	String username;
	private String password;
	private String salt;
	public WordpressUserdetails() {}
	
	public WordpressUserdetails(String username, String password, String salt, GrantedAuthority[] authorities) {
		this.authorities = authorities;
		this.username = username;
		this.password = password;
		this.salt = salt;
	}
	
	public String getSalt() {
		return salt;
	}
	
	@Override
	public GrantedAuthority[] getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
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

	@Override
	public String getUsername() {
		return username;
	}

	public void setTzOffset(float tzOffset) {
		this.tzOffset = tzOffset;
	}

	public float getTzOffset() {
		return tzOffset;
	}

	public void setDaylightSaving(boolean daylightSaving) {
		this.daylightSaving = daylightSaving;
	}

	public boolean isDaylightSaving() {
		return daylightSaving;
	}
}
