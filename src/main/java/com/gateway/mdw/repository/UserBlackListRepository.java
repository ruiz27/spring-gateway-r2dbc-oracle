package com.gateway.mdw.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.gateway.mdw.domain.UserBlackList;

import reactor.core.publisher.Flux;



/**
 * Repository for the entity.
 */
@Repository
public interface UserBlackListRepository extends ReactiveCrudRepository<UserBlackList, Long> {

	@Query("SELECT * FROM USER_BLACK_LIST WHERE " + 
			"(TRUNC(SYSDATE) BETWEEN TRUNC(SINCE_DATE) AND TRUNC(UNTIL_DATE)) " + 
			"OR " + 
			"(TRUNC(SYSDATE) >= TRUNC(SINCE_DATE) AND TRUNC(UNTIL_DATE) IS NULL)")
	Flux<UserBlackList> findBlacklist();

}
