package com.example.berlinclock.controller;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DigitalClockControllerTest {
  
  @Autowired
  private DigitalClockController controller;
  
  @Test
  void testMidnight() {
    Response response = controller.convertToDigital("Y" + "OOOO" + "OOOO" + "OOOOOOOOOOO" + "OOOO");
    assertEquals("00:00", response.getEntity());
    assertEquals(200, response.getStatus());
  }
  
  @Test
  void testHourGreaterThan12() {
    Response response = controller.convertToDigital("O" + "RROORRRO" + "YYROOOOOOOO" + "YYOO");
    assertEquals("13:17", response.getEntity());
    assertEquals(200, response.getStatus());
  }
  
  @Test
  void testNight() {
    Response response = controller.convertToDigital("O" + "RRRRRRRO" + "YYRYYRYYRYY" + "YYYY");
    assertEquals("23:59", response.getEntity());
    assertEquals(200, response.getStatus());
  }
  
  @Test
  void testNoon() {
    Response response = controller.convertToDigital("Y" + "RROORROO" + "OOOOOOOOOOO" + "OOOO");
    assertEquals("12:00", response.getEntity());
    assertEquals(200, response.getStatus());
  }
  
  @Test
  void testAfternoon() {
    Response response = controller.convertToDigital("Y" + "RROORROO" + "YYRYYROOOOO" + "YYOO");
    assertEquals("12:32", response.getEntity());
    assertEquals(200, response.getStatus());
  }
  
  @Test
  void testEndHour() {
    Response response = controller.convertToDigital("O" + "RROORROO" + "YYRYYRYYRYY" + "YOOO");
    assertEquals("12:56", response.getEntity());
    assertEquals(200, response.getStatus());
  }
}
