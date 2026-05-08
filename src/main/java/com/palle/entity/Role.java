package com.palle.entity;

import java.util.Set;

public enum Role {
	ADMIN(Set.of(Permissions.STUDENT_DELETE,Permissions.STUDENT_READ,Permissions.STUDENT_UPDATE,Permissions.STUDENT_WRITE)),
	STUDENT(Set.of(Permissions.STUDENT_READ)),
	TEACHER(Set.of(Permissions.STUDENT_READ,Permissions.STUDENT_UPDATE));
	
	private final Set<Permissions> permissions;
	
	Role(Set<Permissions> permissions){
		this.permissions=permissions;
	}

	public Set<Permissions> getPermissions() {
		return permissions;
	}
	
	
}
