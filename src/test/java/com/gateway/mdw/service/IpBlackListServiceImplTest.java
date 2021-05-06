package com.gateway.mdw.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.gateway.mdw.domain.UserBlackList;
import com.gateway.mdw.repository.UserBlackListRepository;
import com.gateway.mdw.service.impl.UserBlackListServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(MockitoJUnitRunner.class)
public class IpBlackListServiceImplTest {

	@Mock
	UserBlackListRepository ipBlackListRepository;

	@InjectMocks
	UserBlackListServiceImpl ipBlackListServiceImpl;

	@Test
	public void when_ip_is_in_blacklist() {
		// Init DB
		UserBlackList ip = new UserBlackList();
		ip.setIpAddress("62.14.231.58");
		List<UserBlackList> listIpDB = Arrays.asList(ip);
		Flux<UserBlackList> iterable = Flux.fromIterable(listIpDB);

		// Mock DB
		Mockito.when(ipBlackListRepository.findBlacklist()).thenReturn(iterable);

		// Call service
		List<String> listIp = Arrays.asList(new String[] { "62.14.231.58,192.168.128.104", "10.1.1.2" });
		Mono<Boolean> res = ipBlackListServiceImpl.findInBlackList(listIp);

		// Result
		assertTrue(res.block());
	}

	@Test
	public void when_ip_is_not_blacklist() {

		// Init DB
		UserBlackList ip = new UserBlackList();
		ip.setIpAddress("62.14.231.58");
		List<UserBlackList> listIpDB = Arrays.asList(ip);
		Flux<UserBlackList> iterable = Flux.fromIterable(listIpDB);

		// Mock DB
		Mockito.when(ipBlackListRepository.findBlacklist()).thenReturn(iterable);

		// Call service
		List<String> listIp = Arrays.asList(new String[] { "62.14.231.53", "10.1.1.2" });
		Mono<Boolean> res = ipBlackListServiceImpl.findInBlackList(listIp);

		// Result
		assertFalse(res.block());

	}
}
