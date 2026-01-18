package com.example.berlinclock.service.impl;

import com.example.berlinclock.dto.LampColor;
import com.example.berlinclock.service.MinutesLampService;

import java.util.Arrays;

public class MinutesLampServiceImpl implements MinutesLampService {

  private static final int FIVE_MIN_LAMP_COUNT = 11;

  // The upper row represents 5 minute blocks, and is made up of 11 lamps.
  // Every third lamp (positions 2, 5, 8) is red for quarter hours, the rest are yellow.
  @Override
  public LampColor[] getFiveMinutesLamps(int minutes) {
    LampColor[] lamps = new LampColor[FIVE_MIN_LAMP_COUNT];
    Arrays.fill(lamps, LampColor.O);

    int activeCount = minutes / 5;
    for (int i = 0; i < activeCount; i++) {
      // Quarter marks at positions 2, 5, 8 (every 3rd lamp, 0-indexed)
      lamps[i] = isQuarterMark(i) ? LampColor.R : LampColor.Y;
    }
    return lamps;
  }

  private boolean isQuarterMark(int position) {
    return (position + 1) % 3 == 0;
  }
  
  private static final int ONE_MIN_LAMP_COUNT = 4;
  // The bottom row represents 1 minute blocks, and is made up of 4 yellow lamps.
  @Override
  public LampColor[] getOneMinutesLamps(int minutes) {
    LampColor[] lamps = new LampColor[ONE_MIN_LAMP_COUNT];
    Arrays.fill(lamps, LampColor.O);

    int activeCount = minutes % 5;
    for (int i = 0; i < activeCount; i++) {
      lamps[i] = LampColor.Y;
    }
    return lamps;
  }
}
