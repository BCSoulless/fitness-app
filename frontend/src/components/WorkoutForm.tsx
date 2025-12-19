import { useState } from 'react';
import { createWorkout } from '../services/workoutService';

interface WorkoutFormProps {
    onWorkoutAdded: () => void; // A signal to tell the parent "I finished!"
}

function WorkoutForm({ onWorkoutAdded }: WorkoutFormProps) {
    const [name, setName] = useState('');
    const [reps, setReps] = useState('');
    const [weight, setWeight] = useState('');

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        // Convert strings to numbers for the backend
        const workoutData = {
            name: name,
            reps: parseInt(reps),
            weight: parseFloat(weight)
        };

        try {
            await createWorkout(workoutData);
            // Clear the form
            setName('');
            setReps('');
            setWeight('');
            // Tell the App component to refresh the list
            onWorkoutAdded();
        } catch (error) {
            console.error("Failed to add workout", error);
            alert("Error adding workout. Check console.");
        }
    };

    return (
        <form onSubmit={handleSubmit} className="bg-white p-6 rounded-lg shadow-md mb-8 border border-gray-200">
            <h2 className="text-2xl font-bold mb-4 text-gray-800">Add New Workout</h2>

            <div className="grid gap-4 md:grid-cols-3">
                {/* Workout Name */}
                <div>
                    <label className="block text-sm font-medium text-gray-700 mb-1">Exercise Name</label>
                    <input
                        type="text"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        placeholder="e.g. Bench Press"
                        className="w-full p-2 border border-gray-300 rounded focus:ring-2 focus:ring-blue-500 focus:outline-none"
                        required
                    />
                </div>

                {/* Reps */}
                <div>
                    <label className="block text-sm font-medium text-gray-700 mb-1">Reps</label>
                    <input
                        type="number"
                        value={reps}
                        onChange={(e) => setReps(e.target.value)}
                        placeholder="e.g. 10"
                        className="w-full p-2 border border-gray-300 rounded focus:ring-2 focus:ring-blue-500 focus:outline-none"
                        required
                    />
                </div>

                {/* Weight */}
                <div>
                    <label className="block text-sm font-medium text-gray-700 mb-1">Weight (lbs)</label>
                    <input
                        type="number"
                        step="0.5" // Allows decimals
                        value={weight}
                        onChange={(e) => setWeight(e.target.value)}
                        placeholder="e.g. 135.5"
                        className="w-full p-2 border border-gray-300 rounded focus:ring-2 focus:ring-blue-500 focus:outline-none"
                        required
                    />
                </div>
            </div>

            <button
                type="submit"
                className="mt-4 w-full bg-blue-600 text-white py-2 px-4 rounded hover:bg-blue-700 transition font-semibold"
            >
                Add Workout
            </button>
        </form>
    );
}

export default WorkoutForm;