package dk.as.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Graph<V, E> {
	
	private Integer GUID = 0;

	private Map<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();
	private List<Edge> edges = new ArrayList<Edge>();

	public Edge getEdge(int i){	return edges.get(i); }
	public int numVertices(){	return vertices.size(); }
	public int numEdges(){	return edges.size(); }
	public Map<Integer, Vertex> getVertices(){ return vertices; }

	public Edge connect(int a, int b){
		return connect(getVertex(a), getVertex(b));
	}

	protected Edge connect(Vertex a, Vertex b){
		Edge edge = new Edge(a, b);
		a.getEdges().add(edge);
		b.getEdges().add(edge);
		edges.add(edge);
		return edge;
	}

	public final Vertex addVertex(Vertex v){
		vertices.put(GUID++, v);
		return v;
	}

	public final int addData(V data){
		int ret = GUID;
		addVertex(new Vertex(data));
		return ret;
	}

	public Vertex getVertex(Integer i){
		return vertices.get(i);
	}

	public V getData(Integer i){
		return getVertex(i).getData();
	}

	// -------------------------------------------------------------- //
	public class Vertex {

		private V data;
		private List<Edge> edges = new LinkedList<Edge>();

		public Vertex(V data){ this.data = data; }
		public List<Edge> getEdges(){	return edges;	}
		public int numEdges(){ return edges.size(); }
		public V getData(){	return data; }
		public String toString(){	return getData().toString(); }

	}

	public class Edge {

		private Vertex head, tail;
		private E data;

		public Edge(Vertex h, Vertex t){
			head = h;
			tail = t;
		}

		public Vertex getNot(Vertex not){
			return (head == not) ? tail : head;
		}

		public Vertex getHead(){ return head; }
		public Vertex getTail(){ return tail; }
		public E getData(){ return data; }
		public void setData(E data){ this.data = data; }
		public String toString(){
			return "Edge[" 
				+ ((getData() != null) ? getData().toString() : "Void")
				+ "]";
		}

	}

}

