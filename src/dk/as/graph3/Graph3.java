package dk.as.graph3;

import java.util.ArrayList;
import java.util.List;

public interface Graph3<V extends Vertex<E>, E extends Edge<V>> {
	V getVertex(int i);
	E getEdge(int i);
	int numVertices();
	int numEdges();
	E connect(V a, V b);
	void addVertex(V v);
}


