package com.bdefender.enemy;

import com.bdefender.Pair;
import com.bdefender.event.EnemyEvent;
import com.bdefender.event.EventHandler;

public class EnemyFactory {

    public Enemy getEnemy1(EventHandler<EnemyEvent> onDeath, EventHandler<EnemyEvent> onReachedEnd) {
        return this.enemyFromParams(40.0, 40.0, 30.0, 0, onDeath, onReachedEnd);
    }

    public Enemy getEnemy2(EventHandler<EnemyEvent> onDeath, EventHandler<EnemyEvent> onReachedEnd) {
        return this.enemyFromParams(45.0, 50.0, 25.0, 1, onDeath, onReachedEnd);
    }

    public Enemy getEnemy3(EventHandler<EnemyEvent> onDeath, EventHandler<EnemyEvent> onReachedEnd) {
        return this.enemyFromParams(80.0, 30.0, 30.0, 2, onDeath, onReachedEnd);
    }

    private Enemy enemyFromParams(Double life, Double speed, Double damage, Integer typeId,
            EventHandler<EnemyEvent> onDeath, EventHandler<EnemyEvent> onReachedEnd) {
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
            public void takeDamage(Double damage) {
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
            public void setDirection(Pair<Integer, Integer> dir) {
                this.enemyDirection = dir;
            }

        };
    }

}
