package com.gateway.mdw.filter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GlobalPreFilterTest {

	@LocalServerPort
	int port;
	private WebTestClient client;

	@Before
	public void setup() {
		client = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();
	}
	
	@Ignore
	@Test
	@SuppressWarnings("unchecked")
	public void request_token_is_ok() {
		client.get().uri("/token").header("X-ip", "10.1.1.2")
				.exchange()
				.expectStatus().isOk()
				.expectBody(Map.class)
				.consumeWith(result -> {
					assertThat(result.getResponseBody()).isNotEmpty();
				});
	}
	
	@Ignore
	@Test
	public void reuqest_token_is_denid_by_blacklist() throws InterruptedException {
		for(int i=0;i<100;i++) {
			client.get().uri("/token").header("X-ip", "10.1.1.1")
			.exchange()
			.expectStatus().is4xxClientError();
			TimeUnit.MILLISECONDS.sleep(15000);
		}
		
	}
}
