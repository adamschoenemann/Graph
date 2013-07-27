package dk.as.graph2;

public class SimpleEdge<VertType extends Vertex> implements Edge<VertType> {
	private final VertType head, tail;

	public SimpleEdge(VertType t, VertType h){
		head = h;
		tail = t;
	}

	@Override public VertType getHead(){ return head; }
	@Override public VertType getTail(){ return tail; }
	@Override public VertType getNot(VertType not){
		VertType ret = getHead();
		return ret.equals(not) ? tail : head;
	}

}