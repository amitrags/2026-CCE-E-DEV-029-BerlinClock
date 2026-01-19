package com.example.berlinclock.service;

import java.util.Optional;

public interface TimeService {
  Optional<String> validateDigitalTime(String digitalTime);
  Optional<String> validateBerlinTime(String berlinTime);
  String convertToBerlinTime(String digitalTime);
  String convertToDigitalTime(String berlinTime);
}
