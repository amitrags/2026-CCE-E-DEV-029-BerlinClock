package com.example.berlinclock.service.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.example.berlinclock.dto.LampColor;
import static com.example.berlinclock.dto.LampColor.O;
import static com.example.berlinclock.dto.LampColor.R;
import static com.example.berlinclock.dto.LampColor.Y;
import com.example.berlinclock.service.MinutesLampService;

@Service
class MinutesLampServiceImpl implements MinutesLampService {
  
  private static final int FIVE_MIN_LAMP_COUNT = 11;
  private static final int MINUTES_PER_LAMP = 5;
  private static final int INTERVAL_OF_THREE = 3;
  private static final int ONE_MIN_LAMP_COUNT = 4;
  
  // Every third lamp (positions 2, 5, 8) is red for quarter hours, the rest are yellow.
  @Override
  public LampColor[] getFiveMinutesLamps(int minutes) {
    LampColor[] lamps = new LampColor[FIVE_MIN_LAMP_COUNT];
    Arrays.fill(lamps, LampColor.O);
    
    int activeCount = minutes / MINUTES_PER_LAMP;
    
    for (int i = 0; i < activeCount; i++) {
      lamps[i] = isMultipleOfThree(i) ? R : Y;
    }
    return lamps;
  }
  
  private boolean isMultipleOfThree(int position) {
    return (position + 1) % INTERVAL_OF_THREE == 0;
  }

  @Override
  public LampColor[] getOneMinutesLamps(int minutes) {
    LampColor[] lamps = new LampColor[ONE_MIN_LAMP_COUNT];
    Arrays.fill(lamps, O);
    int activeCount = minutes % MINUTES_PER_LAMP;
    Arrays.fill(lamps, 0, activeCount, Y);
    return lamps;
  }
  
  @Override
  public int getMinutes(LampColor[] minuteLamps) {
    int fiveMinutes = countActiveLamps(minuteLamps, 0, FIVE_MIN_LAMP_COUNT) * MINUTES_PER_LAMP;
    int oneMinutes = countActiveLamps(minuteLamps, FIVE_MIN_LAMP_COUNT, FIVE_MIN_LAMP_COUNT + ONE_MIN_LAMP_COUNT);
    return fiveMinutes + oneMinutes;
  }

  private int countActiveLamps(LampColor[] lamps, int from, int to) {
    return  (int )Arrays.stream(lamps, from, to)
            .filter(lamp -> lamp != O)
            .count();
  }
}
