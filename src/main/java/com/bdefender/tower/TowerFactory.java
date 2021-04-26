package com.bdefender.tower;

import com.bdefender.Pair;
import com.bdefender.tower.interactor.ChooseCloserEnemy;
import com.bdefender.tower.interactor.ChooseTargetMethod;
import com.bdefender.tower.interactor.EnemyInteractorDirect;

public class TowerFactory {

    @FunctionalInterface
    interface DamageApplier {
        void applyDamage(int enemyId, int level);
    }

    private static final double NEXT_LEVEL_MULT = 0.25;

    /**
     * Generate a direct shot tower.
     *
     * @param towerName tower name
     * @param ctrl      enemy interactor
     * @param pos       spawn position
     * @return Tower
     */
    public Tower getTowerDirect(final TowerName towerName, final EnemyInteractorDirect ctrl,
            final Pair<Double, Double> pos) {
        return this.towerByParams(towerName.getRangeRadius(), towerName.getShootSpeed(), ctrl,
                pos, towerName.getId(), new ChooseCloserEnemy(),
                (id, level) -> ctrl.applyDamageById(id, towerName.getDamage() + ((level - 1) * NEXT_LEVEL_MULT)));
    }

    private Tower towerByParams(final Double rangeRadius, final Long shootSpeed,
            final EnemyInteractorDirect ctrl, final Pair<Double, Double> pos, final int id,
            final ChooseTargetMethod targetMethod, final DamageApplier damageApplier) {

        return new Tower() {

            private int level = 1;

            @Override
            public Pair<Double, Double> shoot() {
                try {
                    int targetId = this.getOptimalTarget();
                    damageApplier.applyDamage(targetId, this.level);
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
                return targetMethod.getTargetId(ctrl, rangeRadius + level - 1, pos);
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
