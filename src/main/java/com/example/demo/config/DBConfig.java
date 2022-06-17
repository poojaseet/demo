package com.example.demo.config;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionOption;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import java.sql.Driver;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableR2dbcRepositories
public class DBConfig extends AbstractR2dbcConfiguration {
    @Bean("connectFactory")
    @Override
    public ConnectionFactory connectionFactory() {
//        H2ConnectionConfiguration(H2ConnectionConfiguration.builder()
//                .inMemory("demo").
//                property(H2ConnectionOption.DB_CLOSE_DELAY, -1))
//                .build();
        ConnectionFactoryBuilder.withOptions(ConnectionFactoryOptions.builder()
                .option(DRIVER,"h2")
                .option(PROTOCOL,"mem")
                .option(DATABASE,"mem")
                .option(Option.valueOf("ACCESS_MODE_DATA"),"HSQLDB")
                .option(Option.valueOf("DB_CLOSE_DELAY"),"-1"))
           //     .option(Option.valueOf("DB_CLOSE_ON_EXIT"),false,"-1"))
                .build()
                ;
        return null;
    }
}
