package com.lucas.events.dtos;

public record EventRequestDto(int maxParticipants, int registeredParticipants, String date, String title, String description) {
}
