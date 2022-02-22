package dataStructures;

import java.util.HashSet;
import java.util.Set;

import gamePack.Entity;

public class ProximityMap {
	private HashSet<Entity> set = new HashSet<Entity>();
	
	public void add(Entity e) {
		set.add(e);
	}
	
	public void remove(Entity e) {
		set.remove(e);
	}
	
	public Set<Entity> getCloseTo(int x,int y,double distance){
		Set<Entity> res = new HashSet<Entity>();
		for(Entity e : set) {
			if((distance(e.getX(),e.getY(),x,y) <= distance) && (e.getX() != x || e.getY() != y)) {
				res.add(e);
			}
		}
		return res;
	}
	
	private double distance(int x1,int y1,int x2 ,int y2) {
		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2,2));
	}
	
	public void clear() {
		this.set.clear();
	}
}
