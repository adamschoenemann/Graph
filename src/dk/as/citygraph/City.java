package dk.as.citygraph;

public class City {
	public String name;
	public int x, y;

	public City(String name, int x, int y){
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public int getX(){ return x; }
	public int getY(){ return y; }
	public String getName(){ return name; }

	public String toString(){
		return "City(" + name + ", [" + x + ", " + y + "])";
	}
}