package com.riemannroch.wowsetup.view.item;

import com.riemannroch.wowsetup.model.ItemModel;
import com.riemannroch.wowsetup.model.Slot;

public class ItemCompleteView extends ItemView {
    private int intellect;
    private int stamina;
    private int agility;
    private int strength;
    private int spirit;
    private int mp5;
    private int attackPower;
    private int healingPower;
    private int spellPower;
    private int criticalRate;
    private int spellCriticalRate;
    private int haste;
    private int spellHaste;
    private int resilience;

    public ItemCompleteView(ItemModel itemModel){
        super(itemModel);
        this.intellect = itemModel.getIntellect();
        this.stamina = itemModel.getStamina();
        this.agility = itemModel.getAgility();
        this.strength = itemModel.getStrength();
        this.spirit = itemModel.getSpirit();
        this.mp5 = itemModel.getMp5();
        this.attackPower = itemModel.getAttackPower();
        this.healingPower = itemModel.getHealingPower();
        this.spellPower = itemModel.getSpellPower();
        this.criticalRate = itemModel.getCriticalRate();
        this.spellCriticalRate = itemModel.getSpellCriticalRate();
        this.haste = itemModel.getHaste();
        this.spellHaste = itemModel.getSpellHaste();
        this.resilience = itemModel.getResilience();
    }

    public int getIntellect() {
        return intellect;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getSpirit() {
        return spirit;
    }

    public void setSpirit(int spirit) {
        this.spirit = spirit;
    }

    public int getMp5() {
        return mp5;
    }

    public void setMp5(int mp5) {
        this.mp5 = mp5;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getHealingPower() {
        return healingPower;
    }

    public void setHealingPower(int healingPower) {
        this.healingPower = healingPower;
    }

    public int getSpellPower() {
        return spellPower;
    }

    public void setSpellPower(int spellPower) {
        this.spellPower = spellPower;
    }

    public int getCriticalRate() {
        return criticalRate;
    }

    public void setCriticalRate(int criticalRate) {
        this.criticalRate = criticalRate;
    }

    public int getSpellCriticalRate() {
        return spellCriticalRate;
    }

    public void setSpellCriticalRate(int spellCriticalRate) {
        this.spellCriticalRate = spellCriticalRate;
    }

    public int getHaste() {
        return haste;
    }

    public void setHaste(int haste) {
        this.haste = haste;
    }

    public int getSpellHaste() {
        return spellHaste;
    }

    public void setSpellHaste(int spellHaste) {
        this.spellHaste = spellHaste;
    }

    public int getResilience() {
        return resilience;
    }

    public void setResilience(int resilience) {
        this.resilience = resilience;
    }
}
