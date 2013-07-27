package dk.as.graph2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import as.utils.math.Vector2;

public class CityGraph extends SimpleGraph<City, Road> {

	public static CityGraph romania() {
		CityGraph graph = new CityGraph();
		City arad = new City("Arad", 30, 150);
		City zerind = new City("Zerind", 50, 75);
		City oradea = new City("Oradea", 80, 20);
		City timisoara = new City("Timisoara", 32, 210);
		City lugoj = new City("Lugoj", 80, 230);
		City mehadia = new City("Mehadia", 85, 270);
		City drobeta = new City("Drobeta", 80, 310);
		City craiova = new City("Craiova", 130, 320);
		City rimnicu_vilcea = new City("Rimnicu Vilcea", 200, 210);
		City sibiu = new City("Sibiu", 180, 180);
		City fagaras = new City("Fagaras", 260, 185);
		City pitesti = new City("Pitesti", 268, 230);
		City bucharest = new City("Bucharest", 350, 275);
		City giurgio = new City("Giurgiu", 325, 320);
		City urziceni = new City("Urziceni", 390, 250);
		City hirsova = new City("Hirsova", 490, 250);
		City eforie = new City("Eforie", 515, 310);
		City vaslui = new City("Vaslui", 450, 150);
		City iasi = new City("Iasi", 405, 100);
		City neamt = new City("Neamt", 365, 75);
		
		graph.addVertex(arad);
		graph.addVertex(zerind);
		graph.addVertex(oradea);
		graph.addVertex(timisoara);
		graph.addVertex(lugoj);
		graph.addVertex(mehadia);
		graph.addVertex(drobeta);
		graph.addVertex(craiova);
		graph.addVertex(rimnicu_vilcea);
		graph.addVertex(sibiu);
		graph.addVertex(fagaras);
		graph.addVertex(pitesti);
		graph.addVertex(bucharest);
		graph.addVertex(giurgio);
		graph.addVertex(urziceni);
		graph.addVertex(hirsova);
		graph.addVertex(eforie);
		graph.addVertex(vaslui);
		graph.addVertex(iasi);
		graph.addVertex(neamt);
		
		graph.connect(arad, zerind);
		graph.connect(arad, timisoara);
		graph.connect(arad, sibiu);
		graph.connect(zerind, oradea);
		graph.connect(oradea, sibiu);
		graph.connect(timisoara, lugoj);
		graph.connect(lugoj, mehadia);
		graph.connect(mehadia, drobeta);
		graph.connect(drobeta, craiova);
		graph.connect(craiova, pitesti);
		graph.connect(craiova, rimnicu_vilcea);
		graph.connect(sibiu, rimnicu_vilcea);
		graph.connect(rimnicu_vilcea, pitesti);
		graph.connect(sibiu, fagaras);
		graph.connect(fagaras, bucharest);
		graph.connect(pitesti, bucharest);

		graph.connect(bucharest, giurgio);
		graph.connect(bucharest, urziceni);
		graph.connect(urziceni, hirsova);
		graph.connect(hirsova, eforie);

		graph.connect(urziceni, vaslui);
		graph.connect(vaslui, iasi);
		graph.connect(iasi, neamt);
		return graph;
	
	}
	public static void main(String[] args) {
		CityGraph graph = CityGraph.romania();
		Object road = graph.getEdge(0);
		System.out.println(road);
		CityGraphDisplay display = new CityGraphDisplay(graph);
	}

}

class City extends SimpleVertex<City.Data> {

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

	/*@Override
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
	}*/
	
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

class CityGraphDisplay extends JFrame {
	public CityGraph graph;
	public final static int W = 10, H = 10;

	public CityGraphDisplay(CityGraph graph){
		super();
		this.graph = graph;
	}

	protected void frameInit(){
		super.frameInit();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("GraphDisplay");
		setSize(new Dimension(600, 400));
		setLocation(100, 100);
		this.setBackground(Color.WHITE);
		this.setUndecorated(true);
		setResizable(true);
		setVisible(true);
	}

	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.BLACK);
		for (int i = 0; i < graph.numVertices(); i++) {
			City.Data data = graph.getVertex(i).getData();
			g2.draw(new Rectangle.Double(data.x, data.y, W, H));	
			g2.drawString(data.name, (float) data.x, (float) data.y);
		}

		// g2.setColor(Color.RED);
		// for (NNode<City> node: path) {
		// 	City v = node.getData();
		// 	g2.draw(new Rectangle.Double(v.getX(), v.getY(), W, H));	
		// }

		for (int i = 0; i < graph.numEdges(); i++) {
			Road e = (Road) graph.getEdge(i);
			City.Data a = e.getHead().getData();
			City.Data b = e.getTail().getData();
			Vector2 v1 = new Vector2(a.x + W/2, a.y + H/2);
			Vector2 v2 = new Vector2(b.x + W/2, b.y + H/2);
			Vector2 dist = Vector2.from(v1, v2);
			Line2D line = new Line2D.Double(v1.x(), v1.y(), v2.x(), v2.y());		
			g2.setColor(Color.black);
			g2.draw(line);

			g2.setColor(Color.blue);
			g2.drawString(String.valueOf(e.getLength()),
				(float) (v1.x() + dist.traverse(0.5).x()),
				(float) (v1.y() + dist.traverse(0.5).y())
				);
		}
	}
}