package com.bdefender.tower;
import java.util.Map;

import com.bdefender.Pair;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;
import com.bdefender.tower.controller.EnemyControllerDirect;
import com.bdefender.tower.controller.EnemyControllerDirectImpl;


public class TowerFactory {
    /**
     * Generate a direct shot tower.
     * @param pool
     * @param pos
     * @return Tower
     */
    public Tower getTowerDirect1(final EnemiesPoolInteractor pool, final Pair<Double, Double> pos) {
        final Double damage = 15.0;
        final Double rangeRadius = 15.0;
        final Long shootSpeed = 1L;
        final int id = 0;
        return this.towerDirectByParams(damage, rangeRadius, shootSpeed, pool, pos, id);
    }

    /**
     * Generate a direct shot tower.
     * @param pool
     * @param pos
     * @return Tower
     */
    public Tower getTowerDirect2(final EnemiesPoolInteractor pool, final Pair<Double, Double> pos) {
        final Double damage = 10.0;
        final Double rangeRadius = 12.0;
        final Long shootSpeed = 2L;
        final int id = 1;
        return this.towerDirectByParams(damage, rangeRadius, shootSpeed, pool, pos, id);
    }
    /**
     * Generate a zone damage tower.
     * @param pool
     * @param pos
     * @return Tower
     */
    public Tower getTowerZone1(final EnemiesPoolInteractor pool, final Pair<Double, Double> pos) {
        final Double damage = 3.0;
        final Double damageAreaRadius = 5.0;
        final Double rangeRadius = 15.0;
        final Long shootSpeed = 2L;
        final int id = 2;
        return this.towerZoneByParams(damage, damageAreaRadius, rangeRadius, shootSpeed, pool, pos, id);
    }

    private Tower towerZoneByParams(final Double damage, final Double damageAreaRadius, final Double rangeRadius, final Long shootSpeed, final EnemiesPoolInteractor pool, final Pair<Double, Double> pos, final int id) {

        return new Tower() {

            private final EnemyControllerZone enemiesCtrl = new EnemyControllerZoneImpl(pool);

            @Override
            public Pair<Double, Double> shoot() {
                try {
                    final var shootCenter = getOptimalTarget();
                    this.enemiesCtrl.applyDamageByZone(damageAreaRadius, shootCenter, damage);
                    return shootCenter;
                } catch (NoEnemiesAroundException ex) {
                    return null;
                }
            }

            @Override
            public void upgradeLevel() {
                // TODO			
            }

	public Tower getTowerDirect1(final EnemiesPoolInteractor pool, final Pair<Double, Double> pos) {
		return this.towerDirectByParams(20.0, 8.5, 1L, pool, pos, 0);
	}

	public Tower getTowerDirect2(final EnemiesPoolInteractor pool, final Pair<Double, Double> pos) {
		return this.towerDirectByParams(15.0, 9.0, 2L, pool, pos, 1);
	}

	public Tower getTowerDirect3(final EnemiesPoolInteractor pool, final Pair<Double, Double> pos) {
		return this.towerDirectByParams(10.0, 12.0, 2L, pool, pos, 2);
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
            @Override
            public Pair<Double, Double> getPosition() {
                return pos;
            }
        };
    }

}
