package com.riemannroch.wowsetup.model;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TB_ITEM")
public class ItemModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idItem;

    private String name;
    private SlotEnum slotEnum;
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

    //@OneToMany(mappedBy = "item")
    //private Set<ItemEquivalencePoints> itemEquivalencePoints;

    @ManyToMany(mappedBy = "itemsList")
    private List<CharacterModel> ownersList;

    public double equivalencePoints(EquivalencePointSystemModel equivalencePointSystemModel){
        return intellect * equivalencePointSystemModel.getIntellectWeight() +
                stamina * equivalencePointSystemModel.getStaminaWeight() +
                agility * equivalencePointSystemModel.getAgilityWeight() +
                strength * equivalencePointSystemModel.getStrengthWeight() +
                spirit * equivalencePointSystemModel.getSpiritWeight() +
                mp5 * equivalencePointSystemModel.getMp5Weight() +
                attackPower * equivalencePointSystemModel.getAttackPowerWeight() +
                healingPower * equivalencePointSystemModel.getHealingPowerWeight() +
                spellPower * equivalencePointSystemModel.getSpellPowerWeight() +
                criticalRate * equivalencePointSystemModel.getCriticalRateWeight() +
                spellCriticalRate * equivalencePointSystemModel.getSpellCriticalRateWeight() +
                haste * equivalencePointSystemModel.getHasteWeight() +
                spellHaste * equivalencePointSystemModel.getSpellHasteWeight();
    }

    public List<CharacterModel> getOwnersList() {
        return ownersList;
    }

    public void setOwnersList(List<CharacterModel> ownersList) {
        this.ownersList = ownersList;
    }

//    public Set<ItemEquivalencePoints> getItemEquivalencePoints() {
//        return itemEquivalencePoints;
//    }
//
//    public void setItemEquivalencePoints(Set<ItemEquivalencePoints> itemEquivalencePoints) {
//        this.itemEquivalencePoints = itemEquivalencePoints;
//    }

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SlotEnum getSlot() {
        return slotEnum;
    }

    public void setSlot(SlotEnum slotEnum) {
        this.slotEnum = slotEnum;
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
