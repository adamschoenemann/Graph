package dk.as.graph2;

import java.util.List;

public interface Vertex<T> {
	List<Edge<Vertex<T>>> getEdges();
	int numEdges();
	Edge<Vertex<T>> connect(Vertex<T> other);
}