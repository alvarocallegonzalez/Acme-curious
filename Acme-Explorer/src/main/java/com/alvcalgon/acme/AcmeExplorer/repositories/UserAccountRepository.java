package com.alvcalgon.acme.AcmeExplorer.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alvcalgon.acme.AcmeExplorer.bean.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

	Optional<UserAccount> findByUsername(String username);

	@Query(value = "select u from UserAccount u where u.username = ?1")
	UserAccount findByUsername2(String username);

	@Query(value = "select authority a from user_account_authorities where user_account_id = ?1", nativeQuery = true)
	String findAuthorityByUserId(int id);

}
