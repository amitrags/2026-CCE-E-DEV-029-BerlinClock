package com.example.berlinclock.service.impl;

import com.example.berlinclock.dto.LampColor;
import com.example.berlinclock.service.HoursLampService;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import static com.example.berlinclock.dto.LampColor.*;

@Service
class HoursLampServiceImpl implements HoursLampService {

  private static final int LAMP_COUNT = 4;

  //The upper row represents 5 hour blocks and is made up of 4 red lamps.
  @Override
  public LampColor[] getFiveHoursLamps(int hours) {
    LampColor[] lamps = new LampColor[LAMP_COUNT];
    Arrays.fill(lamps, O);

    int litCount = hours / 5;
    Arrays.fill(lamps, 0, litCount, R);
    return lamps;
  }

  //The lower row represents 1 hour blocks and is also made up of 4 red lamps.
  @Override
  public LampColor[] getOneHoursLamps(int hours) {
    LampColor[] lamps = new LampColor[LAMP_COUNT];
    Arrays.fill(lamps, O);

    int litCount = hours % 5;
    Arrays.fill(lamps, 0, litCount, R);
    return lamps;
  }

  @Override
  public int getHours(LampColor[] hoursLamps) {
    return 0;
  }
}
