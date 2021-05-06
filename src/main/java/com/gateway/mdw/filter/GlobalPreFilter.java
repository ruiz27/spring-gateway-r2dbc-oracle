package com.gateway.mdw.filter;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.gateway.mdw.service.UserBlackListService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalPreFilter implements GlobalFilter {

	private final UserBlackListService userBlackListService;

	public GlobalPreFilter(UserBlackListService ipBlackListService) {
		this.userBlackListService = ipBlackListService;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.debug("Global Pre Filter executed");
		return chain.filter(exchange);
	}

	@Bean
	@Order(-1)
	public GlobalFilter blacklistFilter() {
		log.debug("blacklistFilter executed");
		return (exchange, chain) -> {
			List<String> ip = exchange.getRequest().getHeaders().get("X-ip");

			return userBlackListService.findInBlackList(ip).flatMap(onNext -> {
				if (Boolean.TRUE.equals(onNext)) {
					ServerHttpResponse response = exchange.getResponse();
					response.setStatusCode(HttpStatus.UNAUTHORIZED);
					return response.setComplete();
				} else {
					return chain.filter(exchange);
				}
			});
		};
	}

}
