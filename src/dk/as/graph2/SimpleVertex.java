package dk.as.graph2;

import java.util.List;
import java.util.LinkedList;

// Pass in EdgeType as generic? Extending types have to override connect now, to return something other than SimpleEdge
public class SimpleVertex<T> implements Vertex<T> {
	private final List<Edge<Vertex<T>>> edges = new LinkedList<Edge<Vertex<T>>>();
	private T data;

	protected SimpleVertex(){
		
	}
	public SimpleVertex(T data){
		super();
		this.data = data;
	}

	@Override public List<Edge<Vertex<T>>> getEdges(){ return edges; }
	@Override public int numEdges(){ return edges.size(); }
	@Override public Edge<Vertex<T>> connect(Vertex<T> other){
		Edge<Vertex<T>> edge = new SimpleEdge<Vertex<T>>(this, other);
		getEdges().add(edge);
		other.getEdges().add(edge);
		return edge;
	}

	public T getData(){	return data;}
	protected void setData(T d) { data = d; }
}

