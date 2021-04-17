package com.bdefender.tower;

import java.util.Map;
import java.util.Random;

import com.bdefender.Pair;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;
import com.bdefender.tower.controller.EnemyControllerDirect;
import com.bdefender.tower.controller.EnemyControllerDirectImpl;
import com.bdefender.tower.controller.EnemyControllerZone;
import com.bdefender.tower.controller.EnemyControllerZoneImpl;


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

            public Pair<Double, Double> getOptimalTarget() throws NoEnemiesAroundException {

                final Map<Integer, Pair<Double, Double>> enemiesInRange = this.enemiesCtrl.getEnemiesInZone(rangeRadius, pos);

                if (enemiesInRange.isEmpty()) {
                    throw new NoEnemiesAroundException("No enemies around this tower");
                }

                Pair<Integer, Integer> mostEnemiesAround = new Pair<>(0, 0); // la prima cifra(X) rappresenta l'id, la seconda(Y) i nemici che ha attorno

                for (final var enemy : enemiesInRange.entrySet()) {
                    final int enemiesAround = this.enemiesCtrl.getEnemiesInZone(damageAreaRadius, enemy.getValue()).size();
                    if (mostEnemiesAround.getY() <= enemiesAround) {
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

            @Override
            public Pair<Double, Double> getPos() {
                return pos;
            }

            @Override
            public Pair<Double, Double> getPosition() {
                return pos;
            }

        };
    }

    private Tower towerDirectByParams(final Double damage, final Double rangeRadius, final Long shootSpeed, final EnemiesPoolInteractor pool, final Pair<Double, Double> pos, final int id) {

        return new Tower() {

            private EnemyControllerDirect enemiesCtrl = new EnemyControllerDirectImpl(pool);

            @Override
            public Pair<Double, Double> shoot() {
                try {
                    final int targetId = this.getOptimalTarget();
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
                final Map<Integer, Pair<Double, Double>> enemiesInRange = this.enemiesCtrl.getEnemiesInZone(rangeRadius, pos);

                if (enemiesInRange.isEmpty()) {
                    throw new NoEnemiesAroundException("No enemies around this tower");
                }

                Pair<Integer, Double> closerEnemy = new Pair<>(0, rangeRadius + 1);

                for (final var enemy : enemiesInRange.entrySet()) {
                    final double distance = Math.sqrt(Math.pow(enemy.getValue().getX() - pos.getX(), 2) + Math.pow(enemy.getValue().getY() - pos.getY(), 2));
                    if (distance <= closerEnemy.getY()) {
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
