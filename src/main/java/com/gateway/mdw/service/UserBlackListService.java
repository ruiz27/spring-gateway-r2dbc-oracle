package com.gateway.mdw.service;

import java.util.List;

import reactor.core.publisher.Mono;

public interface UserBlackListService {

    Mono<Boolean> findInBlackList(List<String> ip);
}
