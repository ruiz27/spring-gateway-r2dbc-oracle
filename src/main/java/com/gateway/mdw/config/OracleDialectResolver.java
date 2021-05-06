package com.gateway.mdw.config;

import java.util.Optional;

import org.springframework.data.r2dbc.dialect.DialectResolver.R2dbcDialectProvider;
import org.springframework.data.r2dbc.dialect.OracleDialect;
import org.springframework.data.r2dbc.dialect.R2dbcDialect;

import io.r2dbc.spi.ConnectionFactory;

public class OracleDialectResolver implements R2dbcDialectProvider {

	@Override
	public Optional<R2dbcDialect> getDialect(ConnectionFactory connectionFactory) {
		
		if (connectionFactory.getMetadata().getName().contains("Oracle")) {
			return Optional.of(OracleDialect.INSTANCE);
	    }
	    return Optional.empty();
	}
	
	

}
