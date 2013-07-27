package dk.as.citygraph;

import dk.as.search.Problem;
import dk.as.search.Search;
import dk.as.utils.math.Vector2;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class CityGraphDisplay extends JFrame {

	public CityGraphDisplay(CityGraph graph){
		super();
		GraphView gview = new GraphView(graph);
		this.add(gview);
	}

	protected void frameInit(){
		super.frameInit();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("CityGraphDisplay");
		setSize(new Dimension(600, 400));
		setLocation(100, 100);
		this.setBackground(Color.WHITE);
		setResizable(true);
		setVisible(true);
		
	}

	
}

class GraphView extends JPanel {

	public final static int W = 10, H = 10;
	private CityGraph graph;
	private List<CityView> cities = new LinkedList<CityView>();
	private Search search = new Search();
	private CityProblem problem = new CityProblem();

	public GraphView(CityGraph graph){
		super();
		this.graph = graph;

		problem.setInitialState(problem.new State(graph.getVertex(0)));
		problem.setGoalState(problem.new State(graph.getVertex(12)));

		this.setLayout(null);
		for (int i : graph.getVertices().keySet()) {
			CityView view = new CityView(graph.getVertex(i));
			view.setLocation(view.getCity().getX(), view.getCity().getY());
			cities.add(view);
			this.add(view);
		}
		
		refresh(search());
	}

	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);

		for (int i = 0; i < graph.numEdges(); i++) {
			CityGraph.Edge e = graph.getEdge(i);
			City a = e.getHead().getData();
			City b = e.getTail().getData();
			Vector2 v1 = new Vector2(a.getX() + W/2, a.getY() + H/2);
			Vector2 v2 = new Vector2(b.getX() + W/2, b.getY() + H/2);
			Vector2 dist = Vector2.from(v1, v2);
			Line2D line = new Line2D.Double(v1.x(), v1.y(), v2.x(), v2.y());		
			g2.setColor(Color.black);
			g2.draw(line);

			g2.setColor(Color.blue);
			g2.drawString(String.valueOf(Math.round(e.getData())),
				(float) (v1.x() + dist.traverse(0.5).x()),
				(float) (v1.y() + dist.traverse(0.5).y())
			);
		}
	}

	public void setGoal(CityView view){
		problem.setGoalState(problem.new State(view.getVertex()));
	}

	public void setInitial(CityView view){
		problem.setInitialState(problem.new State(view.getVertex()));
	}

	public CityProblem.Solution search(){
		CityProblem.Solution solution = (CityProblem.Solution) search.aStarSearch(problem);
		CityProblem.State init = (CityProblem.State) problem.getInitialState();
		CityProblem.State goal = (CityProblem.State) problem.getGoalState();

		System.out.println("Initial: " + init);
		System.out.println("Goal: " + goal);

		return solution;
	}

	public void refresh(CityProblem.Solution solution){
		for(CityView view : cities){
			view.setSelected(false);
		}
		List<Problem.Action> actions = solution.getActions();

		CityProblem.State state = (CityProblem.State) solution.getNode().getRoot().getState();
		getView(state.getData()).setSelected(true);

		for(Problem.Action action : actions){
			state = (CityProblem.State) action.act(state);
			getView(state.getData()).setSelected(true);
		}
	}

	public CityView getView(CityGraph.Vertex vert){
		for (CityView view : cities) {
			if(view.getVertex().equals(vert)){
				return view;
			}
		}
		return null;
	}


}

class CityView extends JComponent implements MouseListener {

	public final static int W = 10, H = 10;
	public CityGraph.Vertex vertex;
	private boolean selected = false;
	
	public CityView(CityGraph.Vertex vertex){
		this.vertex = vertex;
		super.addMouseListener(this);
		super.setSize(100, 12);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		Point loc = this.getLocation();
		g2.draw(new Rectangle.Double(0, 0, W, H));	
		g2.drawString(getCity().getName(), W + 5, H);
		if(selected){
			g2.setColor(Color.RED);
			g2.fill(new Rectangle.Double(0, 0, W, H));
		}
	}

	public void setSelected(boolean val){ selected = val; }
	public boolean getSelected(){ return selected; }
	public CityGraph.Vertex getVertex(){ return vertex; }
	public City getCity(){ return vertex.getData(); }

	@Override public void mousePressed(MouseEvent e) {
		
		GraphView gv = (GraphView) getParent();
		switch(e.getButton()){
			case MouseEvent.BUTTON1:		
				gv.setInitial(this);
				break;

			case MouseEvent.BUTTON3:
				gv.setGoal(this);
				break;
		}
		gv.refresh(gv.search());
		gv.repaint();
	}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	@Override public void mouseClicked(MouseEvent e) {}
}