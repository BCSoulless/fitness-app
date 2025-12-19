import { useEffect, useState } from 'react';
import { getWorkouts } from './services/workoutService';
import type { Workout } from './types';
import WorkoutForm from './components/WorkoutForm'; // Import the new component

function App() {
  const [workouts, setWorkouts] = useState<Workout[]>([]);
  const [loading, setLoading] = useState(true);

  // Define the fetch function so we can reuse it
  const fetchWorkouts = () => {
    getWorkouts()
      .then((data) => {
        setWorkouts(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching workouts:", error);
        setLoading(false);
      });
  };

  // Run once on load
  useEffect(() => {
    fetchWorkouts();
  }, []);

  return (
    <div className="min-h-screen bg-gray-100 p-8">
      <div className="max-w-4xl mx-auto">
        <h1 className="text-3xl font-bold text-gray-800 mb-6 text-center">
          My Workout Log
        </h1>

        {/* The Form - passing the refresh function as a prop */}
        <WorkoutForm onWorkoutAdded={fetchWorkouts} />

        <hr className="my-8 border-gray-300" />

        {loading ? (
          <p className="text-center text-gray-500">Loading your gains...</p>
        ) : (
          <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-3">
            {workouts.map((workout) => (
              <div
                key={workout.id}
                className="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow border-l-4 border-blue-500"
              >
                <h3 className="text-xl font-semibold text-gray-800 mb-2">
                  {workout.name}
                </h3>
                <div className="text-gray-600 flex justify-between items-center">
                  <span className="bg-blue-100 text-blue-800 text-xs font-medium px-2.5 py-0.5 rounded">
                    {workout.reps} Reps
                  </span>
                  <span className="font-bold">
                    {workout.weight} lbs
                  </span>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

export default App;