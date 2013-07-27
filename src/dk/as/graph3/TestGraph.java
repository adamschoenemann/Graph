package dk.as.graph3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestGraph<V extends Vertex<E>, E extends Edge<V>> implements Graph3<V, E>{

	private List<V> vertices = new ArrayList<V>();
	private List<E> edges = new ArrayList<E>();

	@Override public V getVertex(int i) { return getVertices().get(i); }
	@Override public E getEdge(int i) { return getEdges().get(i); }
	@Override public int numVertices() { return getVertices().size(); }
	@Override public int numEdges() { return getEdges().size(); }
	@Override public void addVertex(V v) { getVertices().add(v); }
	protected List<V> getVertices() { return vertices; }
	protected List<E> getEdges() { return edges; }

	@Override
	public E connect(V a, V b){
		E edge = a.connect(b);
		getEdges().add(edge);
		return edge;
	}

	public E connect(int a, int b){
		return connect(getVertex(a), getVertex(b));
	}

	public static void main(String[] args) {
		TestGraph<BaseVertex, BaseEdge> graph = new TestGraph<BaseVertex, BaseEdge>();
	}

}

class BaseVertex implements Vertex<BaseEdge> {
	private final List<BaseEdge> edges = new LinkedList<BaseEdge>();

	@Override public List<BaseEdge> getEdges(){ return edges; }
	@Override public int numEdges(){ return edges.size(); }
	@Override public BaseEdge connect(Vertex<BaseEdge> other){
		BaseEdge edge = new BaseEdge(this, (BaseVertex) other);
		getEdges().add(edge);
		other.getEdges().add(edge);
		return edge;
	}
	
}

class BaseEdge implements Edge<BaseVertex> {

	private final BaseVertex head, tail;

	public BaseEdge(BaseVertex t, BaseVertex h){
		head = h;
		tail = t;
	}

	@Override public BaseVertex getHead(){ return head; }
	@Override public BaseVertex getTail(){ return tail; }
	@Override public BaseVertex getNot(BaseVertex not){
		BaseVertex ret = getHead();
		return ret.equals(not) ? tail : head;
	}


}

class DataVertex<T> extends BaseVertex {
	private T data;

	public T getData(){ return data;	}
	public void setData(T d){ data = d; }
}
