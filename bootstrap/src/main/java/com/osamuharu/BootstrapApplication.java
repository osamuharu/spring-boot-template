package com.osamuharu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = {"com.osamuharu"})
@ConfigurationPropertiesScan(basePackages = {"com.osamuharu"})
public class BootstrapApplication {

  public static void main(String[] args) {
    SpringApplication.run(BootstrapApplication.class, args);
  }
}