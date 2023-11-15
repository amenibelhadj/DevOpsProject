package tn.esprit.devops_project.service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;



@DataJpaTest
public class DBConnectionTest{

    @TestConfiguration
    static class DatabaseTestConfiguration {
        @Bean
        public DataSource dataSource() {
            // Use an embedded in-memory database for testing
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2) // You can change the type if needed
                    .build();
        }
    }

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDatabaseConnection() {
        if (dataSource != null) {
            System.out.println("Database connection test: Success");
        } else {
            System.out.println("Database connection test: Failed");
        }

        // Verify that the data source is not null, indicating a successful connection to the database
        assertNotNull(dataSource);
    }
}
