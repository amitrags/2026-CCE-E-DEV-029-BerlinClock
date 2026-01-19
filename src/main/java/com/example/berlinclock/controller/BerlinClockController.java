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
@Path("/api/berlin")
public class BerlinClockController {

  private final TimeService timeService;

  public BerlinClockController(TimeService timeService) {
    this.timeService = timeService;
  }
  
  /* Converts a time to Berlin Clock format.
   * Input Example - 13:17:45
   * Output Example - ORROORRROYYROOOOOOOOOYYOO
   */
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response convert(@QueryParam("time") String time) {
    Optional<String> validationError = timeService.validateDigitalTime(time);
    if (validationError.isPresent()) {
      return Response.status(Response.Status.BAD_REQUEST)
              .entity(validationError.get())
              .build();
    }

    String result = timeService.convertToBerlinTime(time);
    return Response.ok(result).build();
  }
}
