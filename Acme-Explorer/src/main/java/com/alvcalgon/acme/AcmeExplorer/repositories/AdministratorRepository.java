package com.alvcalgon.acme.AcmeExplorer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alvcalgon.acme.AcmeExplorer.bean.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

	@Query("select a from Administrator a where a.userAccount.username = ?1")
	Administrator findByUserName(String username);

}
