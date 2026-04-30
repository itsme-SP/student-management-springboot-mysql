package com.palle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.palle.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	Users findByName(String Name);
}