package com.bdefender.tower;

import com.bdefender.Pair;
import com.bdefender.tower.interactor.EnemyInteractorDirect;

import java.util.Map;

public class TowerFactory {

    private static final double NEXT_LEVEL_MULT = 0.25;

    /**
     * Generate a direct shot tower.
     *
     * @param towerName tower name
     * @param ctrl
     * @param pos
     * @return Tower
     */
    public Tower getTowerDirect(final TowerName towerName, final EnemyInteractorDirect ctrl,
            final Pair<Double, Double> pos) {
        return this.towerDirectByParams(towerName.getDamage(), towerName.getRangeRadius(), towerName.getShootSpeed(),
                ctrl, pos, towerName.getId());
    }

    private Tower towerDirectByParams(final Double damage, final Double rangeRadius, final Long shootSpeed,
            final EnemyInteractorDirect ctrl, final Pair<Double, Double> pos, final int id) {

        return new Tower() {

            private int level = 1;

            @Override
            public Pair<Double, Double> shoot() {
                try {
                    int targetId = this.getOptimalTarget();
                    ctrl.applyDamageById(targetId, damage + ((level - 1) * NEXT_LEVEL_MULT));
                    return ctrl.getEnemyPosByID(targetId);
                } catch (NoEnemiesAroundException ex) {
                    return null;
                }
            }

            @Override
            public int upgradeLevel() {
                return ++level;
            }

            private Integer getOptimalTarget() throws NoEnemiesAroundException {
                Map<Integer, Pair<Double, Double>> enemiesInRange = ctrl.getEnemiesInZone(rangeRadius + level - 1, pos);

                if (enemiesInRange.isEmpty()) {
                    throw new NoEnemiesAroundException("No enemies around this tower");
                }

                Pair<Integer, Double> closerEnemy = new Pair<>(0, rangeRadius + 1);

                for (var enemy : enemiesInRange.entrySet()) {
                    double distance = Math.sqrt(Math.pow(enemy.getValue().getX() - pos.getX(), 2)
                            + Math.pow(enemy.getValue().getY() - pos.getY(), 2));
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
            public int getTowerTypeId() {
                return id;
            }

            @Override
            public Pair<Double, Double> getPosition() {
                return pos;
            }

            @Override
            public int getLevel() {
                return this.level;
            }
        };
    }
}
