package com.example.berlinclock.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Component;

@Component
@Path("/api/convert")
public class BerlinClockController {
  
  /* Converts a time to Berlin Clock format.
   * Input Example - 13:17:45
   * Output Example - ORROORRROYYROOOOOOOOOYYOO
   */
  @GET
  @Path("/berlin")
  @Produces(MediaType.TEXT_PLAIN)
  public Response convert(@QueryParam("time") String time) {
    
    return Response.ok().build();
  }
}
