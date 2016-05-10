package com.pluralsight.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pluralsight.model.Goal;

@Repository("goalRepository")
public class GoalRepositoryImpl implements GoalRepository {

	@PersistenceContext
	private EntityManager em;

	public List<Goal> getGoals() {
		Goal goal = null;
		List<Goal> goals = new ArrayList<Goal>();
		for (int i = 1; i < 10; i++) {

			goal = new Goal();
			goal.setMinutes(i);
			goals.add(goal);
		}
		return goals;
	}

	public Goal getGoal(Long id) {
		return em.find(Goal.class, id);
	}

	@Transactional
	public Goal RemoveGoal(Long id) {
		Goal goal = getGoal(id);
		if (goal != null)
			em.remove(goal);
		return goal;
	}

	@Transactional
	public Goal saveGoal(Goal goal) {
		em.persist(goal);
		em.flush();
		return goal;
	}

	@Transactional
	public Goal UpdateGoal(long id, Goal goal) {
		Goal result = getGoal(id);
		if (result == null) {
			System.out.println("creating goal...");
			saveGoal(goal);
		} else {
			System.out.println("merging goal...");
			result.setMinutes(goal.getMinutes());
			em.merge(result);
			em.flush();
		}
		return null;
	}

}
