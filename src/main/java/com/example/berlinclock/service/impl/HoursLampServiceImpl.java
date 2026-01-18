package com.example.berlinclock.service.impl;

import com.example.berlinclock.dto.LampColor;
import com.example.berlinclock.service.HoursLampService;

 class HoursLampServiceImpl implements HoursLampService {
  //The upper row represents 5 hour blocks and is made up of 4 red lamps.
  @Override
  public LampColor[] getFiveHoursLamps(int hours) {
    return new LampColor[4];
  }
  
  //The lower row represents 1 hour blocks and is also made up of 4 red lamps.
  @Override
  public LampColor[] getOneHoursLamps(int hours) {
    return new LampColor[4];
  }
}
