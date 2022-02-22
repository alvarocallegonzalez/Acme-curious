package com.alvcalgon.acme.AcmeExplorer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alvcalgon.acme.AcmeExplorer.bean.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

	@Query("select a from Actor a where a.userAccount.username = ?1")
	Actor findByUserName(String username);

}
