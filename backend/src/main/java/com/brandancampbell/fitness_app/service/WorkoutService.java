package com.brandancampbell.fitness_app.service;

import com.brandancampbell.fitness_app.dto.WorkoutDto;

import java.util.List;

public interface WorkoutService {
    WorkoutDto createWorkout(WorkoutDto workoutDto);

    WorkoutDto getWorkoutById(Long workoutId);

    List<WorkoutDto> getAllWorkouts();

    WorkoutDto updateWorkout(Long workoutId, WorkoutDto workoutDto);

    void deleteWorkout(Long workoutId);
}
