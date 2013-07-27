package dk.as.graph2;

import java.util.List;

public interface Graph<VType extends Vertex> {
	VType getVertex(int i);
	Edge getEdge(int i);
	int numVertices();
	int numEdges();
	Edge connect(VType a, VType b);
	void addVertex(VType v);

}
