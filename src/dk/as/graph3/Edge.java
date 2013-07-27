
package dk.as.graph3;
public interface Edge<V extends Vertex> {
	V getHead();
	V getTail();
	V getNot(V not);
}