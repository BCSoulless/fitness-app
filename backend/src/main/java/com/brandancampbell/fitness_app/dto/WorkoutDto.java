package com.brandancampbell.fitness_app.dto;

public record WorkoutDto(
        Long id,
        String name,
        int reps,
        double weight
) {}
