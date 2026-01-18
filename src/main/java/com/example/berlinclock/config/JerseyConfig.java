package com.example.berlinclock.config;

import com.example.berlinclock.controller.BerlinClockController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
  public JerseyConfig() {
    register(BerlinClockController.class);
  }
}
