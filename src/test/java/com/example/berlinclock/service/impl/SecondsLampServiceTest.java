package com.example.berlinclock.service.impl;

import com.example.berlinclock.dto.LampColor;
import com.example.berlinclock.service.SecondsLampService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecondsLampServiceTest {

  private final SecondsLampService service = new SecondsLampServiceImpl();

  @Test
  void testSecondsLamp() {
    assertEquals(LampColor.Y, service.getSecondsLamp(0));   // even
    assertEquals(LampColor.Y, service.getSecondsLamp(2));
    assertEquals(LampColor.O, service.getSecondsLamp(1));   // odd
    assertEquals(LampColor.O, service.getSecondsLamp(59));
  }

  @Test
  void testGetSeconds() {
    assertEquals(0, service.getSeconds(new LampColor[]{LampColor.Y}));
    assertEquals(1, service.getSeconds(new LampColor[]{LampColor.O}));
  }
}