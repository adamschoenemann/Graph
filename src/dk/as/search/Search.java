package dk.as.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import dk.as.graph.*;

public class Search {
	
	public Problem.Solution breadthFirstSearch(Problem problem){
		Node node = new Node(problem.initialState,
			problem.stepCost(problem.initialState, null));
		Set<Problem.State> explored = new HashSet<Problem.State>();
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(node);
		while(!frontier.isEmpty()) {
			node = frontier.remove();
			explored.add(node.state);

			if(problem.goalTest(node.state)){
				return problem.new Solution(problem, node);
			}
			
			List<Problem.Action> actions = problem.getActions(node.state);
			for (Problem.Action action : actions) {
				Node child = node.makeChild(problem, node, action);
				if(explored.contains(child.state) == false && frontier.contains(node) == false){
					frontier.add(child);
					node.addChild(child);
				}
			}
		}

		return null;
	}

	public Problem.Solution uniformCostSearch(Problem problem){
		Node node = new Node(problem.initialState,
			problem.stepCost(problem.initialState, null));
		Set<Problem.State> explored = new HashSet<Problem.State>();
		Frontier<Node> frontier = new Frontier<Node>();
		frontier.add(node);
		while(!frontier.isEmpty()) {
			node = frontier.remove();
			explored.add(node.state);

			if(problem.goalTest(node.state)){
				return problem.new Solution(problem, node);
			}
			
			List<Problem.Action> actions = problem.getActions(node.state);
			for (Problem.Action action : actions) {
				Node child = node.makeChild(problem, node, action);
				if(explored.contains(child.state) == false && frontier.contains(node) == false){
					frontier.add(child);
					node.addChild(child);
				}
			}
		}

		return null;
	}

}

