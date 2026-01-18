package com.example.berlinclock.service.impl;

import com.example.berlinclock.dto.LampColor;
import com.example.berlinclock.service.MinutesLampService;

public class MinutesLampServiceImpl implements MinutesLampService {
  // The upper row represents 5 minute blocks, and is made up of 11 lamps- every third lamp is red, the rest are yellow.
  @Override
  public LampColor[] getFiveMinutesLamps(int minutes) {
    return new LampColor[11];
  }
  
  // The bottom row represents 1 minute blocks, and is made up of 4 yellow lamps.
  @Override
  public LampColor[] getOneMinutesLamps(int minutes) {
    return new LampColor[4];
  }
}
