package dataStructures;

public class Tuple {
	public static final int prime = 13;
	public int first;
	public int second;
	public Tuple(int first, int second) {
		this.second = second;
		this.first = first;
	}
	
	@Override
	public boolean equals(Object o) {
		Tuple casted = (Tuple) o;
		if(casted.first == this.first && casted.second == this.second) {
			return true;
		}
		return false;
	}
	
	@Override 
	public int hashCode() {
		return ((int)Math.pow(prime , this.first))%this.second;
	}
	
	public double distance(Tuple t) {
		return Math.sqrt(Math.pow(this.first-t.first, 2) + Math.pow(this.second-t.second, 2));
	}
}
