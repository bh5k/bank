package com.pluralsight.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pluralsight.model.Goal;
import com.pluralsight.repository.GoalRepository;
import com.pluralsight.service.GoalService;;

@Path("goals") // http:localhost:8080/exercise-services/webapi/goals
public class GoalResource {

	private ApplicationContext appContext = null;
	private GoalRepository goalRepository;

	public GoalResource() {
		appContext = new ClassPathXmlApplicationContext("jpaContext.xml");
		goalRepository = appContext.getBean("goalRepository", GoalRepository.class);
	}

	//http:localhost:8080/exercise-services/webapi/goals
	/*@GET
	@Produces({ MediaType.APPLICATION_JSON})
	public List<Goal> getGoals() {
		return goalRepository.getGoals();
	}*/
	
	
	@Autowired
	private GoalRepository goalrep;
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	public List<Goal> getGoalsDoNotWork() {
		return goalrep.getGoals();
	}
	
	
	//http:localhost:8080/exercise-services/webapi/goals/1
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("{activityId}")
	public Goal getGoal(@PathParam("activityId") Long Id) {
		System.out.println("Affichage goal par Id...");
		Goal result = goalRepository.getGoal(Id);
		return result;
	}
	
	//http:localhost:8080/exercise-services/webapi/goals/goal
	@POST
	@Path("goal")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Goal createGoal(Goal goal) {
		System.out.println("Cr�ation du goal...");
		goalRepository.saveGoal(goal);
		return goal;
	}
	
	
	// http://localhost:8080/CloudMonitor/webapi/goals/1
	@DELETE
	@Path("{activityId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response removeGoal(@PathParam("activityId") Long Id) {
		System.out.print("D�but suppression");
		Goal result = goalRepository.RemoveGoal(Id);
		if (result !=null)
			System.out.print("remove Success");
		return Response.ok().build();
	}
	
	//http:localhost:8080/exercise-services/webapi/goal
	@PUT
	@Path("{activityId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response UpdateGoal(@PathParam("activityId") Long id,Goal goal) {
		System.out.println("Begin updating goal...");
		goalRepository.UpdateGoal(id,goal);
		return Response.ok().entity(goal).build();
	}

}
