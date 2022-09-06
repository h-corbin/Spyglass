package com.skillstorm.spyglass.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityReference;




@Entity
@Table(name="authorities")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int authorityId;
	
	private String authority = "ROLE_USER";
	
	@ManyToOne
	@JoinColumn(name="username")
	@JsonIdentityReference(alwaysAsId = true)
	private User user;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}
	
	
}
