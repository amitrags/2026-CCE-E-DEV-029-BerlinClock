package com.example.berlinclock.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.example.berlinclock.controller.BerlinClockController;
import com.example.berlinclock.controller.DigitalClockController;

@Configuration
public class JerseyConfig extends ResourceConfig {
  public JerseyConfig() {
    register(BerlinClockController.class);
    register(DigitalClockController.class);
    //packages("src.main.java.com.example.berlinclock.controller");
  }
}
