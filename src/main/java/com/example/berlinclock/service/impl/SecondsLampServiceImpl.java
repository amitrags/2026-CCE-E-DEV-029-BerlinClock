package com.example.berlinclock.service.impl;

import org.springframework.stereotype.Service;

import com.example.berlinclock.dto.LampColor;
import com.example.berlinclock.service.SecondsLampService;

@Service
class SecondsLampServiceImpl implements SecondsLampService {
  
  @Override
  public LampColor getSecondsLamp(int seconds) {
    return (seconds % 2 == 0) ? LampColor.Y : LampColor.O;
  }
  
  //We cannot determine the exact seconds from the lamp, only even/odd.
  //Returns 0 for even (Y), 1 for odd (O)
  @Override
  public int getSeconds(LampColor[] seconds) {
    return seconds[0] == LampColor.Y ? 0 : 1;
  }
}