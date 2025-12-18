package com.brandancampbell.fitness_app.mapper;

import com.brandancampbell.fitness_app.dto.WorkoutDto;
import com.brandancampbell.fitness_app.entity.Workout;

public class WorkoutMapper {
    public static WorkoutDto mapToWorkoutDto(Workout workout) {
        return new WorkoutDto(
                workout.getId(),
                workout.getName(),
                workout.getReps(),
                workout.getWeight()
        );
    }

    public static Workout mapToWorkout(WorkoutDto workoutDto) {
        Workout workout = new Workout();

        workout.setId(workoutDto.id());
        workout.setName(workoutDto.name());
        workout.setReps(workoutDto.reps());
        workout.setWeight(workoutDto.weight());

        return workout;
    }
}
