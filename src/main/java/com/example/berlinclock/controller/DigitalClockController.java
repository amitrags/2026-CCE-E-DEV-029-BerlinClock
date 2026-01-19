package com.example.berlinclock.controller;

import com.example.berlinclock.service.TimeService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Path("/api/digital")
public class DigitalClockController {
  
  private final TimeService timeService;
  
  public DigitalClockController(TimeService timeService) {
    this.timeService = timeService;
  }
  
  /* Converts berlin time to digital time.
   * Input Example - ORROORRROYYROOOOOOOOOYYOO
   * Output Example - 13:17:45
   */
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response convertToDigital(@QueryParam("time") String time) {
    Optional<String> validationError = timeService.validateBerlinTime(time);
    if (validationError.isPresent()) {
      return Response.status(Response.Status.BAD_REQUEST)
              .entity(validationError.get())
              .build();
    }

    String result = timeService.convertToDigitalTime(time);
    return Response.ok(result).build();
  }
}
