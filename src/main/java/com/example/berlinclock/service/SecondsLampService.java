package com.example.berlinclock.service;

import com.example.berlinclock.dto.LampColor;

public interface SecondsLampService {
    LampColor getSecondsLamp(int seconds);
    int getSeconds(LampColor[] seconds);
}