package dk.as.graph2;

public interface Edge<E extends Vertex> {
	E getHead();
	E getTail();
	E getNot(E not);
}