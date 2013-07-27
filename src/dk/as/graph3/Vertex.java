package dk.as.graph3;

import java.util.List;

public interface Vertex<E extends Edge> {
	List<E> getEdges();
	int numEdges();
	E connect(Vertex<E> other);
}