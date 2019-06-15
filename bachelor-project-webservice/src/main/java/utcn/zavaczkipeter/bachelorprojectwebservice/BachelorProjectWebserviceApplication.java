package utcn.zavaczkipeter.bachelorprojectwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities"})
@EnableJpaRepositories(basePackages = {"utcn.zavaczkipeter.bachelorprojectwebservice.dal.repositories"})
public class BachelorProjectWebserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BachelorProjectWebserviceApplication.class, args);
    }

}
