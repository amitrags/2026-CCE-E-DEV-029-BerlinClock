package com.example.berlinclock.service.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.example.berlinclock.dto.LampColor;
import static com.example.berlinclock.dto.LampColor.O;
import static com.example.berlinclock.dto.LampColor.R;
import com.example.berlinclock.service.HoursLampService;

@Service
class HoursLampServiceImpl implements HoursLampService {

  private static final int LAMP_COUNT = 4;

  @Override
  public LampColor[] getFiveHoursLamps(int hours) {
    LampColor[] lamps = new LampColor[LAMP_COUNT];
    Arrays.fill(lamps, O);

    int litCount = hours / 5;
    Arrays.fill(lamps, 0, litCount, R);
    return lamps;
  }

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
    int fiveHours = countActiveLamps(hoursLamps, 0, LAMP_COUNT) * 5;
    int oneHours = countActiveLamps(hoursLamps, LAMP_COUNT, LAMP_COUNT * 2);
    return fiveHours + oneHours;
  }

  private int countActiveLamps(LampColor[] lamps, int from, int to) {
    return (int) Arrays.stream(lamps, from, to)
            .filter(lamp -> lamp != O)
            .count();
  }
}
