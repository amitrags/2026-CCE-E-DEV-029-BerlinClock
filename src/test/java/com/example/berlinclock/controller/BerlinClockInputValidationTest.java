package com.example.berlinclock.controller;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BerlinClockInputValidationTest {
  
  @Autowired
  private BerlinClockController controller;
  
  @Test
  void testMissingTimeParameter() {
    Response response = controller.convert(null);
    assertEquals(400, response.getStatus());
    assertEquals("Missing required parameter: time. Expected format: HH:mm:ss", response.getEntity());
  }
  
  @Test
  void testBlankTimeParameter() {
    Response response = controller.convert(" ");
    assertEquals(400, response.getStatus());
    assertEquals("Missing required parameter: time. Expected format: HH:mm:ss", response.getEntity());
  }
  
  @Test
  void testInvalidTimeFormat() {
    Response response = controller.convert("13:17");
    assertEquals(400, response.getStatus());
    assertEquals("Invalid time format: '13:17'. Expected format: HH:mm:ss (e.g., 13:17:45)", response.getEntity());
  }
  
  @Test
  void testInvalidHours() {
    Response response = controller.convert("25:00:00");
    assertEquals(400, response.getStatus());
  }
  
  @Test
  void testInvalidMinutes() {
    Response response = controller.convert("12:60:00");
    assertEquals(400, response.getStatus());
  }
  
  @Test
  void testInvalidSeconds() {
    Response response = controller.convert("12:00:60");
    assertEquals(400, response.getStatus());
  }
  
  @Test
  void testNonNumericTime() {
    Response response = controller.convert("ab:cd:ef");
    assertEquals(400, response.getStatus());
  }
}
