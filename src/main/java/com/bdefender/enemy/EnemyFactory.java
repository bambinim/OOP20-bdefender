package com.bdefender.enemy;

import com.bdefender.Pair;
import com.bdefender.event.EnemyEvent;
import com.bdefender.event.EventHandler;

public class EnemyFactory {

    public Enemy getEnemy(EnemyName enemy, final EventHandler<EnemyEvent> onDeath,
            final EventHandler<EnemyEvent> onReachedEnd) {
        return this.enemyFromParams(enemy.getLife(), enemy.getSpeed(), enemy.getDamage(), enemy.getId(), onDeath,
                onReachedEnd);
    }

    private Enemy enemyFromParams(final Double life, final Double speed, final Double damage, final Integer typeId,
            final EventHandler<EnemyEvent> onDeath, final EventHandler<EnemyEvent> onReachedEnd) {
        return new Enemy() {

            private Pair<Double, Double> enemyPos;
            private double enemyLife = life;
            private Pair<Integer, Integer> enemyDirection;
            private boolean arrived = false;

            @Override
            public Pair<Double, Double> getPosition() {
                return enemyPos;
            }

            @Override
            public void takeDamage(final Double damage) {
                enemyLife -= damage;
                if (enemyLife <= 0) {
                    onDeath.handle(new EnemyEvent(EnemyEvent.ENEMY_KILLED, this));
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
            public void setArrived(final boolean arrived) {
                this.arrived = arrived;
            }

            @Override
            public void moveTo(final Pair<Double, Double> newPos) {
                enemyPos = newPos;
            }

            @Override
            public double getSpeed() {
                return speed;
            }

            @Override
            public void doDamage() {
                var death = new EnemyEvent(EnemyEvent.ENEMY_REACHED_END, this);
                onReachedEnd.handle(death);
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
            public void setDirection(final Pair<Integer, Integer> dir) {
                this.enemyDirection = dir;
            }

        };
    }

}
