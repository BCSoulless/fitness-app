package com.brandancampbell.fitness_app.repository;

import com.brandancampbell.fitness_app.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}
