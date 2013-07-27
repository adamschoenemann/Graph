package dk.as.search;
import java.util.List;
import java.util.LinkedList;

public class Node {

	public Problem.State state;
	public Problem.Action action;
	public Problem.Cost pathCost;

	public List<Node> children;
	public Node parent = null;

	public Node(Problem.State state, Problem.Cost pathCost){
		this.state = state;
		this.pathCost = pathCost;
		this.children = new LinkedList<Node>();
	}

	public Node makeChild(Problem problem, Node parent, Problem.Action action){
		Node child = new Node(
			problem.result(parent.state, action),
			parent.pathCost.add(problem.stepCost(parent.state, action))
			);
		child.setAction(action);
		return child;
	}

	public Node addChild(Node child){
		child.setParent(this);
		children.add(child);
		return child;
	}

	public Node getRoot(){
		Node node = this;
		while(node.parent != null){
			node = node.parent;
		}
		return node;
	}

	public int numChildren() {	return children.size();	}
	public Node getChildAt(int p) { return children.get(p); }
	public void setParent(Node parent) { this.parent = parent; }
	public Node getParent() { return parent; }

	public Problem.State getState() { return state; }
	public Problem.Action getAction() { return action; }
	public Problem.Cost getPathCost() { return pathCost; }

	public void setState(Problem.State state) { this.state = state; }
	public void setAction(Problem.Action action) { this.action = action; }
	public void setPathCost(Problem.Cost pathCost) { this.pathCost = pathCost; }

	public String print() {
		StringBuilder sb = new StringBuilder();
		this.print(sb, 0);
		return sb.toString();
	}

	private void print(StringBuilder sb, int level){
		for (int i = 0; i < level - 1; i++) {
			sb.append("   ");
		}
		if(level > 0){
			sb.append("`--");
		}
		sb.append(String.valueOf(state)).append("\n");
		for (int i = 0; i < numChildren(); i++) {
			getChildAt(i).print(sb, level + 1);
		}
	}
}