package com.example.berlinclock.controller;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DigitalClockInputValidationTest {
  
  @Autowired
  private DigitalClockController controller;
  
  @Test
  void testMissingTimeParameter() {
    Response response = controller.convertToDigital(null);
    assertEquals(400, response.getStatus());
    assertEquals("Missing required parameter: time", response.getEntity());
  }
  
  @Test
  void testBlankTimeParameter() {
    Response response = controller.convertToDigital(" ");
    assertEquals(400, response.getStatus());
    assertEquals("Missing required parameter: time", response.getEntity());
  }
  
  @Test
  void testInvalidTimeFormat() {
    Response response = controller.convertToDigital("13:17");
    assertEquals(400, response.getStatus());
    assertEquals("Invalid time format: '13:17'. Max length: 24 characters. Allowed characters: R, Y, O", response.getEntity());
  }
  
  @Test
  void testBerlinTimeTooLong() {
    Response response = controller.convertToDigital("Y" + "OOOO" + "OOOO" + "OOOOOOOOOOO" + "OOOO0");
    assertEquals(400, response.getStatus());
  }
  
  @Test
  void testInvalidCharsInMinutes() {
    Response response = controller.convertToDigital("Y" + "OOOO" + "OOOO" + "OOOOOOOOOOO" + "OOOX");
    assertEquals(400, response.getStatus());
  }
  
  @Test
  void testInvalidTimeLength() {
    Response response = controller.convertToDigital("Y" + "OOOO" );
    assertEquals(400, response.getStatus());
  }
  
  @Test
  void testNonValidTime() {
    Response response = controller.convertToDigital("ab:cd:ef");
    assertEquals(400, response.getStatus());
  }
}
