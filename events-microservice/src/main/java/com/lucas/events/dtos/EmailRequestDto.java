package com.lucas.events.dtos;

public record EmailRequestDto(String to, String subject, String text) {
}
