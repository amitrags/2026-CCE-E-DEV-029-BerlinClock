package com.example.berlinclock.dto;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing lamp colors with proper JSON serialization.
 */
public enum LampColor {
  Y('Y'), R('R'), O('O');
  
  private final char symbol;
  
  LampColor(char symbol) {
    this.symbol = symbol;
  }
  
  @JsonValue
  public String getSymbol() {
    return String.valueOf(symbol);
  }
}