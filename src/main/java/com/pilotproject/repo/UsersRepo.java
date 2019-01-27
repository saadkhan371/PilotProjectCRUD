package com.pilotproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pilotproject.model.Users;


@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

}

