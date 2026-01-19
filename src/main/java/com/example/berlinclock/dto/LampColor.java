package com.example.berlinclock.dto;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
/**
 * Enum representing lamp colors with proper JSON serialization.
 */
public enum LampColor {
  Y('Y'), R('R'), O('O');
  
  private final char symbol;
  
  LampColor(char symbol) {
    this.symbol = symbol;
  }
  
  public static LampColor fromChar(char c) {
     return Arrays
            .stream(LampColor.values())
            .filter(lampColor -> (c == lampColor.symbol))
            .toList().get(0) ;
  }
  
  @JsonValue
  public String getSymbol() {
    return String.valueOf(symbol);
  }
}