package com.brandancampbell.fitness_app.service.impl;

import com.brandancampbell.fitness_app.dto.WorkoutDto;
import com.brandancampbell.fitness_app.entity.Workout;
import com.brandancampbell.fitness_app.exception.ResourceNotFoundException;
import com.brandancampbell.fitness_app.mapper.WorkoutMapper;
import com.brandancampbell.fitness_app.repository.WorkoutRepository;
import com.brandancampbell.fitness_app.service.WorkoutService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Override
    public WorkoutDto createWorkout(WorkoutDto workoutDto) {
        Workout workout = WorkoutMapper.mapToWorkout(workoutDto);
        Workout savedWorkout = workoutRepository.save(workout);
        return WorkoutMapper.mapToWorkoutDto(savedWorkout);
    }

    @Override
    public WorkoutDto getWorkoutById(Long workoutId) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new ResourceNotFoundException("Workout not found with id: " + workoutId));

        return WorkoutMapper.mapToWorkoutDto(workout);
    }

    @Override
    public List<WorkoutDto> getAllWorkouts() {
        List<Workout> workouts = workoutRepository.findAll();
        return workouts.stream().map((WorkoutMapper::mapToWorkoutDto))
                .collect(Collectors.toList());
    }

    @Override
    public WorkoutDto updateWorkout(Long workoutId, WorkoutDto updatedWorkout) {
        Workout workout = workoutRepository.findById(workoutId).orElseThrow(
                () -> new ResourceNotFoundException("Workout not found with id: " + workoutId));

        workout.setName(updatedWorkout.name());
        workout.setReps(updatedWorkout.reps());
        workout.setWeight(updatedWorkout.weight());

        Workout updatedWorkoutObj = workoutRepository.save(workout);

        return WorkoutMapper.mapToWorkoutDto(updatedWorkoutObj);
    }

    @Override
    public void deleteWorkout(Long workoutId) {
        Workout workout = workoutRepository.findById(workoutId).orElseThrow(
                () -> new ResourceNotFoundException("Workout not found with id: " + workoutId));

        workoutRepository.deleteById(workoutId);
    }


}
