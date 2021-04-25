package com.bdefender.tower.interactor;

import com.bdefender.Pair;
import com.bdefender.tower.Tower;

@FunctionalInterface
public interface ChooseTargetMethod {
    Integer getTargetId(EnemyInteractor interactor, Double rangeRadius, Pair<Double, Double> pos) throws Tower.NoEnemiesAroundException;
}


