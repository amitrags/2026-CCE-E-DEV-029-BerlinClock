package com.example.berlinclock.service;

import com.example.berlinclock.dto.LampColor;

public interface HoursLampService {
    LampColor[] getFiveHoursLamps(int hours);
    LampColor[] getOneHoursLamps(int hours);
    int getHours(LampColor[] hoursLamps);
}