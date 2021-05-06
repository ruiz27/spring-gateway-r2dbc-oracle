package com.gateway.mdw.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gateway.mdw.repository.UserBlackListRepository;
import com.gateway.mdw.service.UserBlackListService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserBlackListServiceImpl implements UserBlackListService {
	
	private final UserBlackListRepository ipBlackListRepository;

	public UserBlackListServiceImpl(UserBlackListRepository ipBlackListRepository) {
		super();
		this.ipBlackListRepository = ipBlackListRepository;
	}

	@Override
	public Mono<Boolean> findInBlackList(List<String> ipList) {
		log.debug("findIpInBlackList start ");
		return ipBlackListRepository
				.findBlacklist()
				.any(reg -> ipList.stream()
						.anyMatch(ip -> ip.contains(reg.getIpAddress())));
			
	}

}
