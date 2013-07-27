package dk.as.citygraph;

import dk.as.graph.Graph;

public class CityGraph extends Graph<City, Double> {

	@Override
	public Edge connect(Vertex a, Vertex b){
		Edge edge = super.connect(a, b);
		City first = a.getData();
		City second = b.getData();
		double distance = Math.sqrt(Math.pow(second.x - first.x, 2) + Math.pow(second.y - first.y, 2));
		edge.setData(distance);
		return edge;
	}

	public static CityGraph romania() {
		CityGraph graph = new CityGraph();
		City arad_city = new City("Arad", 30, 150);
		City zerind_city = new City("Zerind", 50, 75);
		City oradea_city = new City("Oradea", 80, 20);
		City timisoara_city = new City("Timisoara", 32, 210);
		City lugoj_city = new City("Lugoj", 80, 230);
		City mehadia_city = new City("Mehadia", 85, 270);
		City drobeta_city = new City("Drobeta", 80, 310);
		City craiova_city = new City("Craiova", 130, 320);
		City rimnicu_vilcea_city = new City("Rimnicu Vilcea", 200, 210);
		City sibiu_city = new City("Sibiu", 180, 180);
		City fagaras_city = new City("Fagaras", 260, 185);
		City pitesti_city = new City("Pitesti", 268, 230);
		City bucharest_city = new City("Bucharest", 350, 275);
		City giurgio_city = new City("Giurgiu", 325, 320);
		City urziceni_city = new City("Urziceni", 390, 250);
		City hirsova_city = new City("Hirsova", 490, 250);
		City eforie_city = new City("Eforie", 515, 310);
		City vaslui_city = new City("Vaslui", 450, 150);
		City iasi_city = new City("Iasi", 405, 100);
		City neamt_city = new City("Neamt", 365, 75);
		
		int arad = graph.addData(arad_city);
		int zerind = graph.addData(zerind_city);
		int oradea = graph.addData(oradea_city);
		int timisoara = graph.addData(timisoara_city);
		int lugoj = graph.addData(lugoj_city);
		int mehadia = graph.addData(mehadia_city);
		int drobeta = graph.addData(drobeta_city);
		int craiova = graph.addData(craiova_city);
		int rimnicu_vilcea = graph.addData(rimnicu_vilcea_city);
		int sibiu = graph.addData(sibiu_city);
		int fagaras = graph.addData(fagaras_city);
		int pitesti = graph.addData(pitesti_city);
		int bucharest = graph.addData(bucharest_city);
		int giurgio = graph.addData(giurgio_city);
		int urziceni = graph.addData(urziceni_city);
		int hirsova = graph.addData(hirsova_city);
		int eforie = graph.addData(eforie_city);
		int vaslui = graph.addData(vaslui_city);
		int iasi = graph.addData(iasi_city);
		int neamt = graph.addData(neamt_city);
		
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
		CityGraph.Vertex arad = graph.getVertex(0);
		System.out.println(arad.getData().getName());
		CityGraphDisplay display = new CityGraphDisplay(graph);
	}
}

