package com.pluralsight.repository;

import java.util.List;

import com.pluralsight.model.Goal;

public interface GoalRepository {
	
	Goal saveGoal(Goal goal);
	
	List<Goal> getGoals();
	
	Goal getGoal(Long activityId);
	
	Goal RemoveGoal(Long id);

	Goal UpdateGoal(long id,Goal goal);
	
}
