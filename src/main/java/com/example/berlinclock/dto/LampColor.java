package com.example.berlinclock.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;
/**
 * Enum representing lamp colors with proper JSON serialization.
 */
public enum LampColor {
  Y('Y'), R('R'), O('O');
  
  private final char symbol;
  private static final Map<Character, LampColor> lookupMap = new HashMap<>();

  static {
        for (LampColor color : values()) {
            lookupMap.put(color.symbol, color);
        }
    }
  
  LampColor(char symbol) {
    this.symbol = symbol;
  } 
  
public static LampColor fromChar(char c) {
    LampColor color = lookupMap.get(c);
    if (color == null) {
        throw new IllegalArgumentException("Invalid character for LampColor: " + c);
    }
    return color;
}
  
  @JsonValue
  public String getSymbol() {
    return String.valueOf(symbol);
  }
}