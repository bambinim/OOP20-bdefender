package com.bdefender.tower.controller;

public interface EnemyControllerDirect extends EnemyController {

    /**
     * Takes life from the enemy specified by id.
     * @param id
     * @param damage
     */
    void applyDamageById(Integer id, Double damage);

}
