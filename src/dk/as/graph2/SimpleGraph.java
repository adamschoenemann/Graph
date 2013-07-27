package dk.as.graph2;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class SimpleGraph<VertType extends Vertex, EdgeType extends Edge<VertType>> implements Graph<VertType> {
	
	private List<VertType> vertices = new ArrayList<VertType>();
	private List<EdgeType> edges = new ArrayList<EdgeType>();

	@Override public VertType getVertex(int i) { return getVertices().get(i); }
	@Override public EdgeType getEdge(int i) { return getEdges().get(i); }
	@Override public int numVertices() { return getVertices().size(); }
	@Override public int numEdges() { return getEdges().size(); }
	@Override public void addVertex(VertType v) { getVertices().add(v); }
	protected List<VertType> getVertices() { return vertices; }
	protected List<EdgeType> getEdges() { return edges; }

	@Override
	public EdgeType connect(VertType a, VertType b){
		Edge<VertType> edge = a.connect(b);
		EdgeType ret = (EdgeType) edge;
		getEdges().add(ret);
		return ret;
	}

	public EdgeType connect(int a, int b){
		return connect(getVertex(a), getVertex(b));
	}

	public static <T> SimpleGraph<Vertex<T>, Edge<Vertex<T>>> newInstance(T dataType){
		return new SimpleGraph<Vertex<T>, Edge<Vertex<T>>>();
	}

	public static void main(String[] args) {

		// SimpleGraph<City, Road> graph = new SimpleGraph<City, Road>();
		// graph.addVertex(new City("Arad", 0, 200));
		// graph.getVertex(0).getData();

		/*SimpleGraph<Vertex<String>, Edge<Vertex<String>>> graph = new SimpleGraph<Vertex<String>, Edge<Vertex<String>>>();
		graph = SimpleGraph.newInstance("");
		graph.addVertex(new SimpleVertex<String>("A"));
		graph.addVertex(new SimpleVertex<String>("B"));
		graph.addVertex(new SimpleVertex<String>("C"));
		graph.addVertex(new SimpleVertex<String>("D"));
		graph.addVertex(new SimpleVertex<String>("E"));

		graph.connect(0, 1);
		graph.connect(0, 2);
		graph.connect(0, 3);
		graph.connect(2, 4);
		graph.connect(1, 4);*/
		
	}

}

/*class City extends SimpleVertex<City.Data> {

	public static class Data {
		public String name;
		public double x, y;
		public Data(String n, double x, double y){
			this.name = n;
			this.x = x;
			this.y = y;
		}
	}

	public City(String name, double x, double y){
		super();
		setData(new Data(name, x, y));
	}

	@Override
	public Edge<Vertex<Data>> connect(Vertex<Data> other){
		Data d = getData();
		Data od = ((City) other).getData();
		double len = Math.sqrt(
			Math.pow(d.x - od.x, 2) +
			Math.pow(d.y - od.y, 2)
		);

		// Find out what to do here
		Edge<Vertex<Data>> edge = (Edge<Vertex<Data>>) new Road(this, (City) other, (int) len);
		getEdges().add(edge);
		other.getEdges().add(edge);
		return edge;	
	}
	
}

class Road extends SimpleEdge<City> {
	private int length;

	public Road(City a, City b){
		super(a, b);
		length = (int) Math.sqrt(
			Math.pow(a.getData().x - b.getData().x, 2) +
			Math.pow(a.getData().y - b.getData().y, 2)
		);
	}
	public int getLength(){	return length; }

}
*/

/*public class SimpleGraph<VT, ET extends Vertex<VT>> implements Graph<Vertex<VT>> {
	
	private List<Vertex<VT>> vertices = new ArrayList<Vertex<VT>>();
	private List<Edge<ET>> edges = new ArrayList<Edge<ET>>();

	@Override public Vertex<VT> getVertex(int i) { return getVertices().get(i); }
	@Override public Edge<ET> getEdge(int i) { return getEdges().get(i); }
	@Override public int numVertices() { return getVertices().size(); }
	@Override public int numEdges() { return getEdges().size(); }
	@Override public void addVertex(Vertex<VT> v) { getVertices().add((Vertex<VT>) v);}
	protected List<Vertex<VT>> getVertices() { return vertices; }
	protected List<Edge<ET>> getEdges() { return edges; }

	public SimpleGraph<VT, ET> add(VT data){
		getVertices().add(new Vertex<VT>(data));
		return this;
	}

	@Override
	public Edge<ET> connect(Vertex<VT> a, Vertex<VT> b){
		Edge<ET> edge = a.connect(b);
		getEdges().add(edge);
		return edge;
	}

	public Edge<ET> connect(int a, int b){
		return connect(getVertex(a), getVertex(b));
	}

	public static void main(String[] args) {
		// SimpleGraph<String, SimpleGraph.Edge> graph = new SimpleGraph<String, SimpleGraph.Vertex<String>>();
		// graph
		// 	.add("A")
		// 	.add("B")
		// 	.add("C")
		// 	.add("D")
		// 	.add("E");

		// graph.connect(0, 1);
		// graph.connect(0, 2);
		// graph.connect(0, 3);
		// graph.connect(2, 4);
		// graph.connect(1, 4);
		
	}

	class Vertex<VT> implements Vertex<VT> {
		private final List<Edge> edges;
		private VT data;
		public Vertex(VT data){
			edges = new LinkedList<Edge>();
			this.data = data;
		}

		@Override public List<Edge> getEdges(){ return edges; }
		@Override public int numEdges(){ return edges.size(); }
		@Override public Edge connect(Vertex other){
			Edge edge = new Edge(this, (Vertex) other);
			getEdges().add(edge);
			other.getEdges().add(edge);
			return edge;
		}

		public VT getData(){
			return data;
		}
	}

	class Edge implements Edge {
		private final Vertex head, tail;

		public Edge(Vertex VT, Vertex h){
			head = h;
			tail = VT;
		}

		@Override public Vertex getHead(){ return head; }
		@Override public Vertex getTail(){ return tail; }
		@Override public Vertex getNot(Vertex not){
			Vertex ret = getHead();
			return ret.equals(not) ? tail : head;
		}
	}

}*/