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

import java.util.Arrays;

@Component
@Path("/api/digital")
public class DigitalClockController {
  
  private final SecondsLampService secondsLampService;
  private final HoursLampService hoursLampService;
  private final MinutesLampService minutesLampService;
  
  public DigitalClockController(SecondsLampService secondsLampService,
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
  @Produces(MediaType.TEXT_PLAIN)
  public Response convertToDigital(@QueryParam("time") String berlinTime) {
    String result = getDigitalFormat(berlinTime);
    return Response.ok(result).build();
  }
  
  
  private String getDigitalFormat(String berlinTime) {
    LampColor[] berlinTimeLamps = berlinTime.chars()
            .mapToObj(c -> LampColor.fromChar((char) c))
            .toList().toArray(new LampColor[0]);
    int hours = hoursLampService.getHours(Arrays.copyOfRange(berlinTimeLamps, 1, 9));
    int minutes = minutesLampService.getMinutes(Arrays.copyOfRange(berlinTimeLamps, 9, 24));
    
    return String.format("%02d:%02d", hours, minutes);
  }
}
