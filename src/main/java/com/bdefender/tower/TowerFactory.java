package com.bdefender.tower;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.bdefender.Pair;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;
import com.bdefender.tower.controller.EnemyControllerDirect;
import com.bdefender.tower.controller.EnemyControllerDirectImpl;
import com.bdefender.tower.controller.EnemyControllerZone;
import com.bdefender.tower.controller.EnemyControllerZoneImpl;


public class TowerFactory {

	public Tower getTowerDirect1(final EnemiesPoolInteractor pool, final Pair<Double, Double> pos) {
		return this.towerDirectByParams(15.0, 15.0, 1L, pool, pos, 0);
	}

	public Tower getTowerDirect2(final EnemiesPoolInteractor pool, final Pair<Double, Double> pos) {
		return this.towerDirectByParams(10.0, 12.0, 2L, pool, pos, 1);
	}

	public Tower getTowerZone1(final EnemiesPoolInteractor pool, final Pair<Double, Double> pos) {
		return this.towerZoneByParams(3.0, 5.0, 15.0, 2L, pool, pos, 2);
	}
	
	private Tower towerZoneByParams(Double damage, Double damageAreaRadius, Double rangeRadius, Long shootSpeed, EnemiesPoolInteractor pool, Pair<Double, Double> pos, int id) {

		return new Tower() {

			final EnemyControllerZone enemiesCtrl = new EnemyControllerZoneImpl(pool);
			
			@Override
			public Pair<Double, Double> shoot() {
				try {
					var shootCenter = getOptimalTarget();
					this.enemiesCtrl.applyDamageByZone(damageAreaRadius, shootCenter, damage);
					return shootCenter;
				} catch (NoEnemiesAroundException ex){
					return null;
				}
			}

			@Override
			public void upgradeLevel() {
				// TODO			
			}

			public Pair<Double, Double> getOptimalTarget() throws NoEnemiesAroundException {
								
				Map<Integer, Pair<Double, Double>> enemiesInRange = this.enemiesCtrl.getEnemiesInZone(rangeRadius, pos);
				
				if (enemiesInRange.isEmpty()) {
					throw new NoEnemiesAroundException("No enemies around this tower");
				}
				
				Pair<Integer, Integer> mostEnemiesAround = new Pair<>(0, 0); // la prima cifra(X) rappresenta l'id, la seconda(Y) i nemici che ha attorno

 				for ( var enemy : enemiesInRange.entrySet()) {
					int enemiesAround = this.enemiesCtrl.getEnemiesInZone(damageAreaRadius, enemy.getValue()).size();
					if ( mostEnemiesAround.getY() <= enemiesAround ) {
						mostEnemiesAround = new Pair<>(enemy.getKey(), enemiesAround);
					}
				}
				
				return enemiesInRange.get(mostEnemiesAround.getX());
			}

			@Override
			public long getShootSpeed() {
				return shootSpeed;
			}

			@Override
			public int getTowerId() {
				return id;
			}

		};
	}
	
	private Tower towerDirectByParams(Double damage, Double rangeRadius, Long shootSpeed, EnemiesPoolInteractor pool, Pair<Double, Double> pos, int id) {

		return new Tower() {
			
			EnemyControllerDirect enemiesCtrl = new EnemyControllerDirectImpl(pool);

			@Override
			public Pair<Double, Double> shoot() {
				try {
					int targetId = this.getOptimalTarget();
					this.enemiesCtrl.applyDamageById(targetId, damage);
					return this.enemiesCtrl.getEnemyPosByID(targetId);
				} catch (NoEnemiesAroundException ex) {
					return null;
				}
			}

			@Override
			public void upgradeLevel() {
				// TODO Auto-generated method stub
				
			}

			public Integer getOptimalTarget() throws NoEnemiesAroundException {
				Map<Integer, Pair<Double, Double>> enemiesInRange = this.enemiesCtrl.getEnemiesInZone(rangeRadius, pos);
				
				if (enemiesInRange.isEmpty()) {
					throw new NoEnemiesAroundException("No enemies around this tower");
				}
				
				Pair<Integer, Double> closerEnemy = new Pair<>(0, rangeRadius + 1);
				
				for ( var enemy : enemiesInRange.entrySet()) {
					double distance = Math.sqrt(Math.pow(enemy.getValue().getX() - pos.getX(), 2) + Math.pow(enemy.getValue().getY() - pos.getY(), 2));
					if (distance <= closerEnemy.getY() ) {
						closerEnemy = new Pair<>(enemy.getKey(), distance);
					}
				}
				
				return closerEnemy.getX();
			}

			@Override
			public long getShootSpeed() {
				return shootSpeed;
			}

			@Override
			public int getTowerId() {
				return id;
			}

            @Override
            public Pair<Double, Double> getPos() {
                // TODO Auto-generated method stub
                return null;
            }
		};
	}
	
}
