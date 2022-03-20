package com.barousse.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.barousse.project.domain.User;


@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long>{
	/**
	 * method to find an user by his name in the database
	 * @param name name of the wanted user
	 * @return user found
	 */
	public User findByNameIgnoreCase(String name);

}
