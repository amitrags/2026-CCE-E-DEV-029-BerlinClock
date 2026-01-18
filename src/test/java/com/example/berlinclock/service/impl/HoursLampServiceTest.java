package com.example.berlinclock.service.impl;

import com.example.berlinclock.dto.LampColor;
import com.example.berlinclock.service.HoursLampService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static com.example.berlinclock.dto.LampColor.*;

public class HoursLampServiceTest {

  private final HoursLampService service = new HoursLampServiceImpl();

  @Test
  void testFiveHourLamps() {
    assertArrayEquals(
            new LampColor[] { R, R, O, O },
            service.getFiveHoursLamps(10)
    );
    assertArrayEquals(
            new LampColor[] { R, R, R, O },
            service.getFiveHoursLamps(15)
    );
    assertArrayEquals(
            new LampColor[] { O, O, O, O },
            service.getFiveHoursLamps(4)
    );
    assertArrayEquals(
            new LampColor[] { O, O, O, O },
            service.getFiveHoursLamps(0)
    );
    assertArrayEquals(
            new LampColor[] { R, R, O, O },
            service.getFiveHoursLamps(12)
    );
    assertArrayEquals(
            new LampColor[] { R, R, R, R },
            service.getFiveHoursLamps(24)
    );
  }

  @Test
  void testOneHourLamps() {
    assertArrayEquals(
            new LampColor[] { R, O, O, O },
            service.getOneHoursLamps(1)
    );
    assertArrayEquals(
            new LampColor[] { R, R, R, O },
            service.getOneHoursLamps(13)
    );
    assertArrayEquals(
            new LampColor[] { R, R, R, R },
            service.getOneHoursLamps(4)
    );
    assertArrayEquals(
            new LampColor[] { R, R, O, O },
            service.getOneHoursLamps(12)
    );
    assertArrayEquals(
            new LampColor[] { R, R, R, R },
            service.getOneHoursLamps(24)
    );
  }
}