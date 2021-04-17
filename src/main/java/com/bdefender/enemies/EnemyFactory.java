package com.bdefender.enemies;


import com.bdefender.Pair;

public class EnemyFactory {

	public Enemy getEnemy1(Pair<Double, Double> pos, Pair<Integer, Integer> startDir) {
		return this.enemyFromParams(40.0, 40.0, 30.0, pos, startDir, 0);
	}
	
	public Enemy getEnemy2(Pair<Double, Double> pos, Pair<Integer, Integer> startDir) {
		return this.enemyFromParams(45.0, 50.0, 25.0, pos, startDir, 1);
	}


	public Enemy getEnemy3(Pair<Double, Double> pos, Pair<Integer, Integer> startDir) {
		return this.enemyFromParams(80.0, 30.0, 30.0, pos, startDir, 2);
	}
	
	private Enemy enemyFromParams(Double life, Double speed, Double damage, Pair<Double, Double> pos, Pair<Integer, Integer> startDir, Integer typeId) {
		return new Enemy() {
			
			private Pair<Double, Double> enemyPos = pos;
			private double enemyLife = life;
			private Pair<Integer, Integer> enemyDirection = startDir;
			private EnemyStateChanged onDead;
			private boolean arrived = false;
			
			@Override
			public Pair<Double, Double> getPosition() {
				return enemyPos;
			}

			@Override
			public void takeDamage(Double damage) {
				enemyLife -= damage;
				if (enemyLife <= 0){
					onDead.StateChanged();
				}
			}

			@Override
			public boolean isAlive() {
				return enemyLife > 0;
			}

			@Override
			public boolean isArrived() {
				return this.arrived;
			}

			@Override
			public void setArrived(boolean arrived) {
				this.arrived = arrived;
			}

			@Override
			public void moveTo(Pair<Double, Double> newPos) {
				enemyPos = newPos;
			}

			@Override
			public double getSpeed() {
				return speed;
			}

			@Override
			public double getLife() {
				return this.enemyLife;
			}

			@Override
			public void setOnDeadAction(EnemyStateChanged onDead) {
				this.onDead = onDead;
			}

			@Override
			public Integer getTypeId() {
				return typeId;
			}

			@Override
			public Pair<Integer, Integer> getDirection() {
				return this.enemyDirection;
			}

			@Override
			public void setDirection(Pair<Integer, Integer> dir) {
				this.enemyDirection = dir;				
			}
			
		};
	}
	
}
