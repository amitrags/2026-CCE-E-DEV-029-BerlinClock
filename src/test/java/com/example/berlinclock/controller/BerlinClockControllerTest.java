package com.example.berlinclock.controller;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for BerlinClockController based on the Berlin Clock Kata.
 *
 * @see <a href="https://agilekatas.co.uk/katas/BerlinClock-Kata">Berlin Clock Kata</a>
 */
@SpringBootTest
class BerlinClockControllerTest {
  
  @Autowired
  private BerlinClockController controller;
  
  @Test
  void testMidnight() {
    Response response = controller.convert("00:00:00");
    assertEquals("Y" + "OOOO" + "OOOO" + "OOOOOOOOOOO" + "OOOO", response.getEntity());
    assertEquals(200, response.getStatus());
  }
  
  @Test
  void testHourGreaterThan12() {
    Response response = controller.convert("13:17:01");
    assertEquals("O" + "RROORRRO" + "YYROOOOOOOO" + "YYOO", response.getEntity());
    assertEquals(200, response.getStatus());
  }
  
  @Test
  void testNight() {
    Response response = controller.convert("23:59:59");
    assertEquals("O" + "RRRRRRRO" + "YYRYYRYYRYY" + "YYYY", response.getEntity());
    assertEquals(200, response.getStatus());
  }
  
  @Test
  void testNoon() {
    Response response = controller.convert("12:00:00");
    assertEquals("Y" + "RROORROO" + "OOOOOOOOOOO" + "OOOO", response.getEntity());
    assertEquals(200, response.getStatus());
  }
  
  @Test
  void testAfternoon() {
    Response response = controller.convert("12:32:00");
    assertEquals("Y" + "RROORROO" + "YYRYYROOOOO" + "YYOO", response.getEntity());
    assertEquals(200, response.getStatus());
  }
  
  @Test
  void testEndHour() {
    Response response = controller.convert("12:56:01");
    assertEquals("O" + "RROORROO" + "YYRYYRYYRYY" + "YOOO", response.getEntity());
    assertEquals(200, response.getStatus());
  }
}
