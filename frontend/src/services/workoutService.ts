import axios from "axios"
import type { Workout } from "../types";

const API_URL = "http://localhost:8080/api/workouts"

export const getWorkouts = async () => {
    const response = await axios.get<Workout[]>(API_URL);
    return response.data;
};

export const createWorkout = async (workout: Omit<Workout, "id">) => {
    const response = await axios.post<Workout>(API_URL, workout);
    return response.data
}