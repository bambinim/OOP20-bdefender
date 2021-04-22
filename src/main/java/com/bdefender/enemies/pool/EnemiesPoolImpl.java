package com.bdefender.enemies.pool;
import com.bdefender.Pair;
import com.bdefender.enemies.Enemy;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;
import com.bdefender.enemies.pool.EnemiesPoolMover;
import com.bdefender.enemies.pool.EnemiesPoolSpawner;
import com.bdefender.enemies.pool.MapInteractor;
import com.bdefender.enemies.view.EnemyGraphicMover;
import com.bdefender.map.Coordinates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class EnemiesPoolImpl implements EnemiesPoolInteractor, EnemiesPoolMover, EnemiesPoolSpawner {

	static class EnemyReachedEndException extends Exception{}


	
	private Map<Integer,Enemy> enemies = new HashMap<>();
	private int counter = 0;
	private final MapInteractor mapInteractor;
	private final EnemyGraphicMover graphicMover;
	
	public EnemiesPoolImpl(MapInteractor mapInteractor, EnemyGraphicMover graphicMover) {
		this.mapInteractor = mapInteractor;
		this.graphicMover = graphicMover;
	}
	
	@Override
	public Map<Integer, Enemy> getEnemies(boolean alive){
		return alive ? this.getAliveEnemies() : this.enemies;
	}

	public void ClearPool(){
		this.enemies = new HashMap<>();
	}

	private Map<Integer,Enemy> getAliveEnemies(){
		return this.enemies.entrySet().stream().filter(e -> e.getValue().isAlive() && !e.getValue().isArrived()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
	
	@Override
	public void addEnemy(Enemy enemy) {
		enemy.moveTo(mapInteractor.getSpawnPoint());
		enemy.setDirection(this.mapInteractor.getStartingDirection());
		enemies.put(counter++,enemy);
	}

	@Override
	public void applyDamageById(final int id, Double damage) {
		this.enemies.get(id).takeDamage(damage);
		System.out.println("Enemy " + id + " got damaged");
		if (!enemies.get(id).isAlive()) {
			System.out.println("Enemy " + id + " died");
		}
	}

	private Pair<Double, Double> getNextPos(Pair<Integer, Integer> dir, Pair<Double, Double> currPos, Pair<Double, Double> distance){
		double newX = currPos.getX() + (distance.getX() * dir.getX());
		double newY = currPos.getY() + (distance.getY() * dir.getY());
		return new Pair<>(newX, newY);
	}



	private boolean isAfterKeyPoint(Pair<Double, Double> p1, Pair<Double, Double> p2, Pair<Integer, Integer> dir){
		return (((p1.getX() - p2.getX())  > 0 && dir.getX() == 1) || (p1.getX().equals(p2.getX()) && dir.getX() == 0 )) && (p1.getY() - p2.getY()) * dir.getY() >= 0;
	}

	private boolean keyPointIsAfter(Pair<Double, Double> p1, Pair<Double, Double> p2, Pair<Integer, Integer> dir){
		return (p1.getX() >= p2.getX()) && (p1.getY() - p2.getY()) * dir.getY() >= 0;
	}

	private Pair<Double, Double> getNextValidPos(Pair<Double, Double> nextPosSimple, Enemy enemy) throws EnemyReachedEndException{
		ArrayList<Pair<Double, Double>> keyPoints = new ArrayList<>(this.mapInteractor.getKeyPoints());
		Pair<Integer, Integer> dir = enemy.getDirection();
		Pair<Double, Double> currPos = enemy.getPosition();
		Pair<Double, Double> nxtPos = nextPosSimple;
		for (Pair<Double, Double> keyPoint : keyPoints) {
			if (keyPointIsAfter(keyPoint, currPos, dir) && isAfterKeyPoint(nxtPos, keyPoint, dir)) {
				int nextXDir = dir.getX() == 0 ? 1 : 0;
				int nextYDir = 0;
				if (keyPoints.indexOf(keyPoint) + 1 == keyPoints.size()) {
					throw new EnemyReachedEndException();
				} else {
					Double nextKeyPointY = keyPoints.get(keyPoints.indexOf(keyPoint) + 1).getY();
					if (!nextKeyPointY.equals(keyPoint.getY())) {
						nextYDir = nextKeyPointY > keyPoint.getY() ? 1 : -1;
					}
					enemy.setDirection(new Pair<>(nextXDir, nextYDir));
					nxtPos = getNextPos(enemy.getDirection(), keyPoint, new Pair<>(Math.abs(nxtPos.getY() - keyPoint.getY()), Math.abs(nxtPos.getX() - keyPoint.getX())));
				}
				break;
			}
		}
		return nxtPos;
	}

	@Override
	public void moveEnemies () {
		synchronized (this) {
			for (Enemy enemy : this.enemies.values()) {
				if (enemy.isAlive() && !enemy.isArrived()) {
					try {
						enemy.moveTo(getNextValidPos(getNextPos(enemy.getDirection(), enemy.getPosition(), new Pair<>(enemy.getSpeed() / 1000, enemy.getSpeed() / 1000)), enemy));
					} catch (EnemyReachedEndException ex) {
						enemy.setArrived(true);
						enemy.doDamage();
					}
				}
			}
			this.graphicMover.moveEnemies(new ArrayList<>(this.getAliveEnemies().values()));
		}
	}

}
