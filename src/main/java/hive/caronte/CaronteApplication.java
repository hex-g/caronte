package hive.caronte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableEurekaClient
@EntityScan( basePackages = {"hive.ishigami.entity"} )
@PropertySource("classpath:ishigami.properties")
public class CaronteApplication {

  public static void main(String[] args) {
    SpringApplication.run(CaronteApplication.class, args);
  }

}
