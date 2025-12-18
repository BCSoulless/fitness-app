package com.brandancampbell.fitness_app.controller;

import com.brandancampbell.fitness_app.dto.WorkoutDto;
import com.brandancampbell.fitness_app.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    public ResponseEntity<WorkoutDto> createWorkout(@RequestBody WorkoutDto workoutDto) {
        WorkoutDto savedWorkout = workoutService.createWorkout(workoutDto);
        return new ResponseEntity<>(savedWorkout, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkoutDto> getWorkoutById(@PathVariable("id") Long workoutId) {
        WorkoutDto workoutDto = workoutService.getWorkoutById(workoutId);
        return ResponseEntity.ok(workoutDto);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutDto>> getAllWorkouts() {
        List<WorkoutDto> workouts = workoutService.getAllWorkouts();
        return ResponseEntity.ok(workouts);
    }

    @PutMapping("{id}")
    public ResponseEntity<WorkoutDto> updateWorkout(@PathVariable("id") Long workoutId,
                                                    @RequestBody WorkoutDto updatedWorkout) {
        WorkoutDto workoutDto = workoutService.updateWorkout(workoutId, updatedWorkout);
        return ResponseEntity.ok(workoutDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteWorkout(@PathVariable("id") Long workoutId) {
        workoutService.deleteWorkout(workoutId);
        return ResponseEntity.ok("Deleted workout with id " + workoutId);
    }
}
