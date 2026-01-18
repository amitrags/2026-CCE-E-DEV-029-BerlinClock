package com.example.berlinclock.controller;

import com.example.berlinclock.dto.LampColor;
import com.example.berlinclock.service.HoursLampService;
import com.example.berlinclock.service.MinutesLampService;
import com.example.berlinclock.service.SecondsLampService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Component
@Path("/api/convert")
public class BerlinClockController {
  
  private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
  private static final Pattern TIME_PATTERN = Pattern.compile("^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$");
  
  private final SecondsLampService secondsLampService;
  private final HoursLampService hoursLampService;
  private final MinutesLampService minutesLampService;
  
  public BerlinClockController(SecondsLampService secondsLampService,
                               HoursLampService hoursLampService,
                               MinutesLampService minutesLampService) {
    this.secondsLampService = secondsLampService;
    this.hoursLampService = hoursLampService;
    this.minutesLampService = minutesLampService;
  }
  
  /* Converts a time to Berlin Clock format.
   * Input Example - 13:17:45
   * Output Example - ORROORRROYYROOOOOOOOOYYOO
   */
  @GET
  @Path("/berlin")
  @Produces(MediaType.TEXT_PLAIN)
  public Response convert(@QueryParam("time") String time) {
    // Validate input
    if (time == null || time.isBlank()) {
      return Response.status(Response.Status.BAD_REQUEST)
              .entity("Missing required parameter: time. Expected format: HH:mm:ss")
              .build();
    }
    
    if (!TIME_PATTERN.matcher(time).matches()) {
      return Response.status(Response.Status.BAD_REQUEST)
              .entity("Invalid time format: '" + time + "'. Expected format: HH:mm:ss (e.g., 13:17:45)")
              .build();
    }
    LocalTime localTime = LocalTime.parse(time, TIME_FORMATTER);
    
    int hours = localTime.getHour();
    int minutes = localTime.getMinute();
    int seconds = localTime.getSecond();
    
    String result = getBerlinFormat(seconds, hours, minutes);
    return Response.ok(result).build();
  }
  
  private String getBerlinFormat(int seconds, int hours, int minutes) {
    StringBuilder sb = new StringBuilder();
    appendLamp(sb, secondsLampService.getSecondsLamp(seconds));
    appendLamps(sb, hoursLampService.getFiveHoursLamps(hours));
    appendLamps(sb, hoursLampService.getOneHoursLamps(hours));
    appendLamps(sb, minutesLampService.getFiveMinutesLamps(minutes));
    appendLamps(sb, minutesLampService.getOneMinutesLamps(minutes));

    return sb.toString();
  }

  private void appendLamp(StringBuilder sb, LampColor lamp) {
    sb.append(lamp.name());
  }

  private void appendLamps(StringBuilder sb, LampColor[] lamps) {
    for (LampColor lamp : lamps) {
      sb.append(lamp.name());
    }
  }
}
