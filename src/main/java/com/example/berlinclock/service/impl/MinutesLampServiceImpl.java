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
  
  // Every third lamp (positions 2, 5, 8) is red for quarter hours, the rest are yellow.
  @Override
  public LampColor[] getFiveMinutesLamps(int minutes) {
    LampColor[] lamps = new LampColor[FIVE_MIN_LAMP_COUNT];
    Arrays.fill(lamps, LampColor.O);
    
    int activeCount = minutes / 5;
    
    for (int i = 0; i < activeCount; i++) {
      lamps[i] = isMultipleOfThree(i) ? R : Y;
    }
    return lamps;
  }
  
  private boolean isMultipleOfThree(int position) {
    return (position + 1) % 3 == 0;
  }
  
  private static final int ONE_MIN_LAMP_COUNT = 4;
  
  @Override
  public LampColor[] getOneMinutesLamps(int minutes) {
    LampColor[] lamps = new LampColor[ONE_MIN_LAMP_COUNT];
    Arrays.fill(lamps, O);
    int activeCount = minutes % 5;
    Arrays.fill(lamps, 0, activeCount, Y);
    return lamps;
  }
  
  @Override
  public int getMinutes(LampColor[] minuteLamps) {
    int fiveMinutes = countActiveLamps(minuteLamps, 0, FIVE_MIN_LAMP_COUNT) * 5; //weight 5
    int oneMinutes = countActiveLamps(minuteLamps, FIVE_MIN_LAMP_COUNT, FIVE_MIN_LAMP_COUNT + ONE_MIN_LAMP_COUNT);//weight 1
    return fiveMinutes + oneMinutes;
  }

  private int countActiveLamps(LampColor[] lamps, int from, int to) {
    return  (int )Arrays.stream(lamps, from, to)
            .filter(lamp -> lamp != O)
            .count();
  }
}
