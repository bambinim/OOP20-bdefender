package com.bdefender.tower.interactor;

public interface EnemyInteractorDirect extends EnemyInteractor {

    /**
     * Takes life from the enemy specified by id.
     *
     * @param id     enemy id.
     * @param damage damage amount.
     */
    void applyDamageById(Integer id, Double damage);

}
