package com.softeng.athleticsticket.model;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_id")
	private int id;
	@Column(name="role")
	private String role;

	public Role() {
		// Default constructor
	}

	public int getId() {
		return id;
	}
	public void setId(final int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(final String role) {
		this.role = role;
	}
	
	
}
