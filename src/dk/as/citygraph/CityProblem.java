package dk.as.citygraph;
import java.util.LinkedList;
import java.util.List;
import dk.as.graph.*;
import dk.as.search.Problem;
import dk.as.search.Node;
import dk.as.search.Frontier;
import dk.as.search.Search;

public class CityProblem extends Problem {

	// -----------------------------CityProblem methods--------------------------
	public List<Problem.Action> getActions(Problem.State state){
		CityGraph.Vertex vertex = ((State) state).getData();
		List<Problem.Action> actions = new LinkedList<Problem.Action>();
		int i = 0;
		for (CityGraph.Edge road : vertex.getEdges()) {
			actions.add(new Action(i));
			i++;
		}
		return actions;
	}

	public Problem.State result(Problem.State state, Problem.Action action){
		return action.act(state);
	}

	public Problem.Cost stepCost(Problem.State state, Problem.Action action){
		if(state.equals(initialState)){
			return new Cost(0);
		}
		State cityState = (State) state;	
		CityGraph.Vertex city = cityState.getData();
		Action pathAction = (Action) action;
		return new Cost(city.getEdges().get(pathAction.direction).getData());
	}


	public static void main(String[] args) {
		CityGraph graph = CityGraph.romania();

		CityProblem cityProblem = new CityProblem();
		CityGraph.Vertex arad = graph.getVertex(0);
		CityGraph.Vertex bucharest = graph.getVertex(12);

		System.out.println("initial: " + arad);
		System.out.println("goal: " + bucharest);

		cityProblem.setInitialState(cityProblem.new State(arad));
		cityProblem.setGoalState(cityProblem.new State(bucharest));

		Search search = new Search();

		CityProblem.Solution solution = search.breadthFirstSearch(cityProblem);
		Node node = solution.getNode();
		System.out.println(node.getRoot().print());
		System.out.println("pathCost: " + node.getPathCost().get());
		System.out.println("Actions: " + solution.getActions());
		while(node != null){
			System.out.println(node.getState() + " Action: " + node.getAction());
			node = node.getParent();
		}
		System.out.println();
		System.out.println();

		solution = search.uniformCostSearch(cityProblem);
		node = solution.getNode();
		System.out.println("pathCost: " + node.getPathCost().get());
		System.out.println("Actions: " + solution.getActions());
		System.out.println(node.getRoot().print());
		while(node != null){
			System.out.println(node.getState() + " Action: " + node.getAction());
			node = node.getParent();
		}
	}

	/**
	 * -------------------------STATE----------------------------
	 */
	public class State extends Problem.State {
		private CityGraph.Vertex vertex;

		public State(CityGraph.Vertex vertex){
			this.vertex = vertex;
		}

		@Override
		public CityGraph.Vertex getData(){
			return vertex;
		}

		@Override
		public boolean equals(Object other){
			if(other == null || !(other instanceof State))
				return false;
			if(other == this)
				return true;
			if(hashCode() == ((State) other).hashCode()){
				return  true;
			}
			return false;
		}

		@Override
		public int hashCode(){
			return getCity().getName().hashCode();
		}

		public City getCity(){
			return vertex.getData();
		}
	}

	// ----------------------------------COST-----------------------------
	public class Cost implements Problem.Cost {
		public double length;
		public Cost(double len){
			this.length = len;
		}

		@Override
		public Problem.Cost add(Problem.Cost cost){
			return new Cost(length + ((Cost) cost).length);
		}

		@Override
		public int compareTo(Problem.Cost other){
			return Double.compare(length, ((Cost) other).length);
		}

		@Override
		public Double get(){
			return new Double(length);
		}
	}

	// -------------------------------------ACTION------------------------------
	public class Action implements Problem.Action {
		public int direction;
		public Action(int direction){
			this.direction = direction;
		}
		public Problem.State act(Problem.State state){
			State cityState = (State) state;
			CityGraph.Vertex from = cityState.getData();
			CityGraph.Vertex to = cityState.getData().getEdges().get(direction).getNot(from);
			return new State(to);
		}

		public Integer get(){
			return new Integer(direction);
		}

		public String toString(){
			return "Action[" + String.valueOf(direction) + "]";
		}
	}



}


