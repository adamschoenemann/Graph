package dk.as.citygraph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import dk.as.utils.math.Vector2;

public class CityGraphDisplay extends JFrame {

	private CityGraph graph;	
	public final static int W = 10, H = 10, Y_OFFSET = 50;;

	public CityGraphDisplay(CityGraph graph){
		super();
		this.graph = graph;
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

	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(0, Y_OFFSET);
		g2.setColor(Color.BLACK);
		for (int i : graph.getVertices().keySet()) {
			City v = graph.getData(i);
			g2.draw(new Rectangle.Double(v.getX(), v.getY(), W, H));	
			g2.drawString(v.getName(), (float) v.getX(), (float) v.getY());
		}

		// g2.setColor(Color.RED);
		// for (NNode<City> node: path) {
		// 	City v = node.getData();
		// 	g2.draw(new Rectangle.Double(v.getX(), v.getY(), W, H));	
		// }

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
		g2.translate(0, -Y_OFFSET);
	}
}