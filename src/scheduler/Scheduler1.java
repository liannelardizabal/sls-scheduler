package scheduler;

import java.util.Random;

/*
 * Author: Lianne Lardizabal
 */
public class Scheduler1 implements Scheduler {

	/*
	 * Modification of Scheduler to implement greedy descent with random walks.
	 * 
	 * 1) Randomly select a course from a room, time, course, and day.
	 * 2) Assign these values to an new instance called ScheduleChoice.
	 * 3) If a conflict exists, take a random step to the next neighbour.
	 *
	 */
	 
	public ScheduleChoice[] schedule(SchedulingProblem pProblem) {

		Random r = new Random();

		// Initialize problem parameters.
		Course[] courses = pProblem.getCourseList();
		Room[] rooms = pProblem.getRoomList();
		int[] times = new int[4];
		int[] days = new int[5];
		
		// Set loop limit to terminate the optimization after a given time limit.
		int looplimit = 0;

		ScheduleChoice[] schedules = new ScheduleChoice[courses.length];

		// Initialize list of ScheduleChoices.
		for (int i = 0; i < schedules.length; i++) {
			ScheduleChoice schedule = new ScheduleChoice(
					courses[i], 
					rooms[r.nextInt(rooms.length)], 
					times[r.nextInt(4)], 
					days[r.nextInt(5)]);

			schedules[i] = schedule;
		}

		// Loop through each schedule choice and ensure that it is a different schedule.
		for (int i = 0; i < schedules.length; i++) {		

			// Access object member variables for comparison.
			ScheduleChoice s = schedules[i];
			int t = s.getTimeSlot();
			int d = s.getDay();
			Room p = s.getRoom();

			// Loops terminates based on loop limit constraint.
			if (looplimit > 50000000){
				break;
			}

			// Compare with other schedule choices.
			// If conflict exists, randomly re-assign new values to current schedule choice based on a neighbour.
		    // Reset counter and loop through schedule choice list from the beginning.

			for (int j = i+1 ; j < schedules.length; j++ ) {	

				if ( t == schedules[j].getTimeSlot() && d == schedules[j].getDay() && p.equals(schedules[j].getRoom())) {

					// Initialize new ScheduleChoice object (non-mutatable).
					ScheduleChoice tempSchedule = new ScheduleChoice(
							schedules[i].getCourse(), 
							rooms[r.nextInt(rooms.length)], 
							times[r.nextInt(4)], 
							days[r.nextInt(5)]);

					schedules[i] = tempSchedule;
					j = i+1;
					looplimit++;
				}

				if (looplimit > 50000000){
					break;
				}
			}
		}

		return schedules;
	}
}
