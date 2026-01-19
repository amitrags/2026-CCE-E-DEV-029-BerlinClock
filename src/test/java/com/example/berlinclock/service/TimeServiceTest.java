package com.example.berlinclock.service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TimeServiceTest {
  
  @Autowired
  private TimeService timeService;
  
  @Nested
  class DigitalTimeValidation {
    
    @Test
    void validTime() {
      Optional<String> result = timeService.validateDigitalTime("13:17:45");
      assertTrue(result.isEmpty());
    }
    
    @Test
    void nullTimeError() {
      Optional<String> result = timeService.validateDigitalTime(null);
      assertTrue(result.isPresent());
      assertEquals("Missing required parameter: time. Expected format: HH:mm:ss", result.get());
    }
    
    @Test
    void blankTimeError() {
      Optional<String> result = timeService.validateDigitalTime(" ");
      assertTrue(result.isPresent());
      assertEquals("Missing required parameter: time. Expected format: HH:mm:ss", result.get());
    }
    
    @Test
    void invalidFormaError() {
      Optional<String> result = timeService.validateDigitalTime("13:17");
      assertTrue(result.isPresent());
      assertEquals("Invalid time format: '13:17'. Expected format: HH:mm:ss (e.g., 13:17:45)", result.get());
    }
    
    @Test
    void invalidHourError() {
      Optional<String> result = timeService.validateDigitalTime("25:00:00");
      assertTrue(result.isPresent());
    }
    
    @Test
    void invalidMinuteError() {
      Optional<String> result = timeService.validateDigitalTime("12:60:00");
      assertTrue(result.isPresent());
    }
    
    @Test
    void invalidSecondError() {
      Optional<String> result = timeService.validateDigitalTime("12:00:60");
      assertTrue(result.isPresent());
    }
    
    @Test
    void charTimeReturnsError() {
      Optional<String> result = timeService.validateDigitalTime("ab:cd:ef");
      assertTrue(result.isPresent());
    }
  }
  
  @Nested
  class BerlinTimeValidation {
    
    @Test
    void validTIme() {
      Optional<String> result = timeService.validateBerlinTime("YOOOOOOOOOOOOOOOOOOOOOOO");
      assertTrue(result.isEmpty());
    }
    
    @Test
    void nullTime() {
      Optional<String> result = timeService.validateBerlinTime(null);
      assertTrue(result.isPresent());
      assertEquals("Missing required parameter: time", result.get());
    }
    
    @Test
    void blankError() {
      Optional<String> result = timeService.validateBerlinTime(" ");
      assertTrue(result.isPresent());
      assertEquals("Missing required parameter: time", result.get());
    }
    
    @Test
    void invalidFormaError() {
      Optional<String> result = timeService.validateBerlinTime("13:17");
      assertTrue(result.isPresent());
      assertEquals("Invalid time format: '13:17'. Max length: 24 characters. Allowed characters: R, Y, O", result.get());
    }
    
    @Test
    void badLengthError() {
      Optional<String> result = timeService.validateBerlinTime("YOOOOOOOOOOOOOOOOOOOOOOOO");
      assertTrue(result.isPresent());
    }
    
    @Test
    void invalidCharError() {
      Optional<String> result = timeService.validateBerlinTime("YOOOOOOOOOOOOOOOOOOOOOX");
      assertTrue(result.isPresent());
    }
    
    @Test
    void shortLengthError() {
      Optional<String> result = timeService.validateBerlinTime("YOOOO");
      assertTrue(result.isPresent());
    }
  }
  
  @Nested
  class DigitalToBerlinConversion {
    
    @Test
    void convertMidnight() {
      String result = timeService.convertToBerlinTime("00:00:00");
      assertEquals("Y" + "OOOO" + "OOOO" + "OOOOOOOOOOO" + "OOOO", result);
    }
    
    @Test
    void convertHourGreaterThan12() {
      String result = timeService.convertToBerlinTime("13:17:01");
      assertEquals("O" + "RROO" + "RRRO" + "YYROOOOOOOO" + "YYOO", result);
    }
    
    @Test
    void convertNight() {
      String result = timeService.convertToBerlinTime("23:59:59");
      assertEquals("O" + "RRRR" + "RRRO" + "YYRYYRYYRYY" + "YYYY", result);
    }
    
    @Test
    void convertNoon() {
      String result = timeService.convertToBerlinTime("12:00:00");
      assertEquals("Y" + "RROO" + "RROO" + "OOOOOOOOOOO" + "OOOO", result);
    }
    
    @Test
    void convertAfternoon() {
      String result = timeService.convertToBerlinTime("12:32:00");
      assertEquals("Y" + "RROO" + "RROO" + "YYRYYROOOOO" + "YYOO", result);
    }
    
    @Test
    void convertEndHour() {
      String result = timeService.convertToBerlinTime("12:56:01");
      assertEquals("O" + "RROO" + "RROO" + "YYRYYRYYRYY" + "YOOO", result);
    }
  }
  
  @Nested
  class BerlinToDigitalConversion {
    
    @Test
    void convertMidnight() {
      String result = timeService.convertToDigitalTime("Y" + "OOOO" + "OOOO" + "OOOOOOOOOOO" + "OOOO");
      assertEquals("00:00", result);
    }
    
    @Test
    void convertHourGreaterThan12() {
      String result = timeService.convertToDigitalTime("O" + "RROO" + "RRRO" + "YYROOOOOOOO" + "YYOO");
      assertEquals("13:17", result);
    }
    
    @Test
    void convertNight() {
      String result = timeService.convertToDigitalTime("O" + "RRRR" + "RRRO" + "YYRYYRYYRYY" + "YYYY");
      assertEquals("23:59", result);
    }
    
    @Test
    void convertNoon() {
      String result = timeService.convertToDigitalTime("Y" + "RROO" + "RROO" + "OOOOOOOOOOO" + "OOOO");
      assertEquals("12:00", result);
    }
    
    @Test
    void convertAfternoon() {
      String result = timeService.convertToDigitalTime("Y" + "RROO" + "RROO" + "YYRYYROOOOO" + "YYOO");
      assertEquals("12:32", result);
    }
    
    @Test
    void convertEndHour() {
      String result = timeService.convertToDigitalTime("O" + "RROO" + "RROO" + "YYRYYRYYRYY" + "YOOO");
      assertEquals("12:56", result);
    }
  }
}
