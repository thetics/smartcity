package com.example.smarticity.base;

import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;

import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.Connection;
import java.sql.SQLException;


public abstract class AbstractTest {

    @Autowired
    protected ApplicationContext ctx;

    @Autowired
    protected HikariDataSource dataSource;

    @Autowired
    Flyway flyway;

    private static boolean isInitialized = false;

    @BeforeClass
    public static void init() {
        isInitialized = false;
    }

    /**
     * Truncate the all tables in DB and reinitialize with data sql scripts
     */
    @Before
    protected void setup() throws SQLException {
        if (isInitialized) return;

        Connection conn = dataSource.getConnection();
        ResourceDatabasePopulator rdp = new ResourceDatabasePopulator(
//                ctx.getResource("classpath:db_populations/truncate.sql"),
//                ctx.getResource("classpath:db_migrations/V1__create_first_tableTEST.sql"),
//                ctx.getResource("classpath:db_migrations/V2__add_stars_to_hotel.sql"),
//                ctx.getResource("classpath:db_migrations/V3__add_status_reservation.sql"),
//                ctx.getResource("classpath:db_migrations/V4__insert_User.sql")

        );
        rdp.populate(conn);
        conn.close();

        isInitialized = true;
    }

}
