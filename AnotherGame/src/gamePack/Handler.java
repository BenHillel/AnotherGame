package gamePack;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import dataStructures.ProximityMap;

public class Handler {
	private LinkedList<GameObject> layer1,layer2,layer3;
	private LinkedList<Button> buttonLayer;
	private HashMap<GameObject,Integer> gameObjectToLayer = new HashMap<GameObject,Integer>();
	private ProximityMap proximityMap = new ProximityMap();
	private Set<EnemyShip> enemies = new HashSet<EnemyShip>();
	public int numOfEnemies;
	public Handler() {
		this.layer1 = new LinkedList<GameObject>();
		this.layer2 = new LinkedList<GameObject>();
		this.layer3 = new LinkedList<GameObject>();
		this.buttonLayer = new LinkedList<Button>();
		this.numOfEnemies = 0;
		
	}
	
	
	public void tick() {
		for(int i=0;i<layer1.size();i++) {
			layer1.get(i).tick();
		}
		for(int i=0;i<layer2.size();i++) {
			layer2.get(i).tick();
		}
		for(int i=0;i<layer3.size();i++) {
			layer3.get(i).tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i=0;i<buttonLayer.size();i++) {
			buttonLayer.get(i).render(g);
		}
		for(int i=0;i<layer1.size();i++) {
			layer1.get(i).render(g);
		}
		for(int i=0;i<layer2.size();i++) {
			layer2.get(i).render(g);
		}
		for(int i=0;i<layer3.size();i++) {
			layer3.get(i).render(g);
		}

	}
	
	public void addGameObject(GameObject obj,int layer) {
		switch(layer) {
		case 1:
			this.layer1.add(obj);
			gameObjectToLayer.put(obj, 1);
			break;
		case 2:
			this.layer2.add(obj);
			gameObjectToLayer.put(obj, 2);
			break;
		case 3:
			this.layer3.add(obj);
			gameObjectToLayer.put(obj, 3);
			break;
		default:
			this.layer3.add(obj);
			gameObjectToLayer.put(obj, 3);
			break;
		}
		if(obj instanceof Entity){
			proximityMap.add((Entity)obj);
			if(obj instanceof EnemyShip) {
				this.numOfEnemies++;
				this.enemies.add((EnemyShip)obj);
			}

		}
		
	}
	
	public void addButton(Button button) {
		buttonLayer.add(button);
	}

	public void removeGameObject(GameObject obj) {
		if(obj instanceof Button) {
			removeButton((Button)obj);
			return;
		}
		try {
			switch(gameObjectToLayer.get(obj)) {
			case 1 :
				layer1.remove(obj);
				gameObjectToLayer.remove(obj);
				break;
			case 2 :
				layer2.remove(obj);
				gameObjectToLayer.remove(obj);
				break;
			case 3 :
				layer3.remove(obj);
				gameObjectToLayer.remove(obj);
				break;
			default:
				System.err.println("object not found");
			}
			if(obj instanceof Entity){
				proximityMap.remove((Entity)obj);
				if(obj instanceof EnemyShip) {
					this.numOfEnemies--;
					this.enemies.remove((EnemyShip)obj);
				}
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
		}

	}
	
	public void removeButton(Button b) {
		buttonLayer.remove(b);
	}
	
	public void clearAll() {
		layer1.clear();
		layer2.clear();
		layer3.clear();
		gameObjectToLayer.clear();
		proximityMap.clear();
		buttonLayer.clear();
		this.numOfEnemies = 0;
		
	}
	
	public boolean press(int mx,int my) {
		for(int i=0;i<buttonLayer.size();i++) {
			if(buttonLayer.get(i).press(mx, my)) return true;
		}
		return false;
	}
	
	public EnemyShip getEnemyAt(int mx,int my) {
		for(EnemyShip e : enemies) {
			if(e.contains(mx, my)) return e;
		}
		return null;
	}
	
	public Set<Entity> getObjectsCloseTo(Entity e,int distance){
		return proximityMap.getCloseTo(e.getX(), e.getY(),distance );
	}
	public Set<Entity> getObjectsCloseTo(Projectile e,int distance){
		return proximityMap.getCloseTo(e.getX(), e.getY(),distance );
	}
	
}
