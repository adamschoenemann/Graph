package dk.as.search;

import java.util.LinkedList;
import java.util.List;

public abstract class Problem {

	public State initialState;
	public State goalState;

	public abstract List<Action> getActions(State state);
	public abstract State result(State state, Action action);
	public abstract Cost stepCost(State state, Action action);
	public void setInitialState(State init) {	initialState = init; }
	public State getInitialState() { return initialState;	}
	public void setGoalState(State goal) { goalState = goal;}
	public State getGoalState() {	return goalState;	}

	// --------------------- ACTION --------------------------------------- //
	public static interface Action {

		public State act(State state);
		public Object get();

	}

	// --------------------- STATE --------------------------------------- //
	public abstract class State {

		public abstract Object getData();
		public String toString() {return getData().toString();}

	}

	// --------------------- SOLUTION --------------------------------------- //
	public class Solution {

		private Problem problem;
		private Node node;

		public Solution(Problem problem, Node node) {
			this.problem = problem;
			this.node = node;
		}

		public List<Action> getActions(){
			List<Action> actions = new LinkedList<Action>();
			Node n = node;
			while(n.getParent() != null) {
				actions.add(0, n.getAction());
				n = n.getParent();
			} 
			return actions;
		}

		public Node getNode(){ return node; }
		public Problem getProblem(){ return problem; }

	} 

	// --------------------- COST --------------------------------------- //
	public interface Cost extends Comparable<Cost> {
		public Cost add(Cost a);
		public Object get();
	}

	public boolean goalTest(State state) {
		if(state.equals(goalState)) {
			return true;
		}
		return false;
	}



}

