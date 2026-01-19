package com.example.berlinclock.service;

import com.example.berlinclock.dto.LampColor;

public interface MinutesLampService {
    LampColor[] getFiveMinutesLamps(int minutes);
    LampColor[] getOneMinutesLamps(int minutes);
    int getMinutes(LampColor[] minuteLamps);
}