package dk.as.search;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
public class Frontier<T extends Node> extends PriorityQueue<T> {

	public Frontier() {
		super(11,
			new Comparator<T>() {
				@Override
				public int compare(T a, T b) {
					return a.getPathCost().compareTo(b.getPathCost());
				}
			}
		);
	}

	@Override
	public boolean contains(Object query) {
		if(query == null || !(query instanceof Node)) {
			return false;
		}
		Iterator<T> iter = this.iterator();
		while(iter.hasNext()) {
			T node = iter.next();
			if(node.getState().equals(((T) query).getState())) {
				return true;
			}
		}
		return false;
	}
}
