package com.example.backerp;

import com.example.backerp.Repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.Filter;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
public class BackErpApplication {
    @Autowired
    private UtilisateurRepository UR ;

    public static void main(String[] args) {
        SpringApplication.run(BackErpApplication.class, args);
    }

    @Bean
    public Filter OpenFilter(){
          return new OpenEntityManagerInViewFilter();
    }
}
