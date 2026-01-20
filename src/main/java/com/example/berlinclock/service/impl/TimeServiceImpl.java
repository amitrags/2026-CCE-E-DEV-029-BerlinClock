package com.example.berlinclock.service.impl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.example.berlinclock.dto.LampColor;
import com.example.berlinclock.service.HoursLampService;
import com.example.berlinclock.service.MinutesLampService;
import com.example.berlinclock.service.SecondsLampService;
import com.example.berlinclock.service.TimeService;

@Service
class TimeServiceImpl implements TimeService {
  
  private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
  private static final Pattern DIGITAL_TIME_PATTERN = Pattern.compile("^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$");
  private static final Pattern BERLIN_TIME_PATTERN = Pattern.compile("^[RYO]{24}$");
  
  private final SecondsLampService secondsLampService;
  private final HoursLampService hoursLampService;
  private final MinutesLampService minutesLampService;
  
  TimeServiceImpl(SecondsLampService secondsLampService, HoursLampService hoursLampService, MinutesLampService minutesLampService) {
    this.secondsLampService = secondsLampService;
    this.hoursLampService = hoursLampService;
    this.minutesLampService = minutesLampService;
  }
  
  @Override
  public Optional<String> validateDigitalTime(String digitalTime) {
    if (digitalTime == null || digitalTime.isBlank()) {
      return Optional.of("Missing required parameter: time. Expected format: HH:mm:ss");
    }
    
    if (!DIGITAL_TIME_PATTERN.matcher(digitalTime).matches()) {
      return Optional.of("Invalid time format: '" + digitalTime + "'. Expected format: HH:mm:ss (e.g., 13:17:45)");
    }
    
    return Optional.empty();
  }
  
  @Override
  public Optional<String> validateBerlinTime(String berlinTime) {
    if (berlinTime == null || berlinTime.isBlank()) {
      return Optional.of("Missing required parameter: time");
    }
    
    if (!BERLIN_TIME_PATTERN.matcher(berlinTime).matches()) {
      return Optional.of("Invalid time format: '" + berlinTime + "'. Max length: 24 characters. Allowed characters: R, Y, O");
    }
    
    return Optional.empty();
  }
  
  @Override
  public String convertToBerlinTime(String digitalTime) {
    LocalTime localTime = LocalTime.parse(digitalTime, TIME_FORMATTER);
    
    int hours = localTime.getHour();
    int minutes = localTime.getMinute();
    int seconds = localTime.getSecond();
    
    StringBuilder sb = new StringBuilder();
    appendLamp(sb, secondsLampService.getSecondsLamp(seconds));
    appendLamps(sb, hoursLampService.getFiveHoursLamps(hours));
    appendLamps(sb, hoursLampService.getOneHoursLamps(hours));
    appendLamps(sb, minutesLampService.getFiveMinutesLamps(minutes));
    appendLamps(sb, minutesLampService.getOneMinutesLamps(minutes));
    
    return sb.toString();
  }
  
  @Override
  public String convertToDigitalTime(String berlinTime) {
    LampColor[] berlinTimeLamps = berlinTime.chars()
                                  .mapToObj(c -> LampColor.fromChar((char) c))
                                  .toArray(LampColor[]::new);
    
    int hours = hoursLampService.getHours(Arrays.copyOfRange(berlinTimeLamps, 1, 9));
    int minutes = minutesLampService.getMinutes(Arrays.copyOfRange(berlinTimeLamps, 9, 24));
    
    return String.format("%02d:%02d", hours, minutes);
  }
  
  private void appendLamp(StringBuilder sb, LampColor lamp) {
    sb.append(lamp.getSymbol());
  }
  
  private void appendLamps(StringBuilder sb, LampColor[] lamps) {
    for (LampColor lamp : lamps) {
      sb.append(lamp.getSymbol());
    }
  }
}
