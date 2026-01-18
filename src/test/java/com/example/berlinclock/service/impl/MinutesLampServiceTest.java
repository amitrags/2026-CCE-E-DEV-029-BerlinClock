package com.example.berlinclock.service.impl;

import com.example.berlinclock.dto.LampColor;
import com.example.berlinclock.service.MinutesLampService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MinutesLampServiceTest {

  private final MinutesLampService service = new MinutesLampServiceImpl();

  @Test
  void testFiveMinutesLamps() {
    // 17 mins -> 3 lamps lit (17 / 5 = 3)
    assertArrayEquals(
            new LampColor[] {
                    LampColor.Y, LampColor.Y, LampColor.R,
                    LampColor.O, LampColor.O, LampColor.O,
                    LampColor.O, LampColor.O, LampColor.O,
                    LampColor.O, LampColor.O
            },
            service.getFiveMinutesLamps(17)
    );
    // 55 mins -> 11 lamps with reds at 3 lamps
    assertArrayEquals(
            new LampColor[] {
                    LampColor.Y, LampColor.Y, LampColor.R,
                    LampColor.Y, LampColor.Y, LampColor.R,
                    LampColor.Y, LampColor.Y, LampColor.R,
                    LampColor.Y, LampColor.Y
            },
            service.getFiveMinutesLamps(55)
    );
    // 0 mins -> all off
    assertArrayEquals(
            new LampColor[] {
                    LampColor.O, LampColor.O, LampColor.O,
                    LampColor.O, LampColor.O, LampColor.O,
                    LampColor.O, LampColor.O, LampColor.O,
                    LampColor.O, LampColor.O
            },
            service.getFiveMinutesLamps(0)
    );
    // 2 mins -> all off
    assertArrayEquals(
            new LampColor[] {
                    LampColor.O, LampColor.O, LampColor.O,
                    LampColor.O, LampColor.O, LampColor.O,
                    LampColor.O, LampColor.O, LampColor.O,
                    LampColor.O, LampColor.O
            },
            service.getFiveMinutesLamps(2)
    );
  }

  @Test
  void testOneMinutesLamps() {
    assertArrayEquals(
            new LampColor[] { LampColor.Y, LampColor.O, LampColor.O, LampColor.O },
            service.getOneMinutesLamps(1)
    );
    assertArrayEquals(
            new LampColor[] { LampColor.O, LampColor.O, LampColor.O, LampColor.O },
            service.getOneMinutesLamps(5)
    );
    assertArrayEquals(
            new LampColor[] { LampColor.Y, LampColor.Y, LampColor.O, LampColor.O },
            service.getOneMinutesLamps(12)
    );
    assertArrayEquals(
            new LampColor[] { LampColor.Y, LampColor.Y, LampColor.Y, LampColor.Y },
            service.getOneMinutesLamps(59)
    );
    assertArrayEquals(
            new LampColor[] { LampColor.O, LampColor.O, LampColor.O, LampColor.O },
            service.getOneMinutesLamps(0)
    );
  }
}