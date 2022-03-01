package com.riemannroch.wowsetup.model;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "TB_EQUIVALENCEPOINTSYSTEM")
public class EquivalencePointSystemModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idEquivalencePointSystem;

    private String name;
    private double intellectWeight;
    private double staminaWeight;
    private double agilityWeight;
    private double strengthWeight;
    private double spiritWeight;
    private double mp5Weight;
    private double attackPowerWeight;
    private double healingPowerWeight;
    private double spellPowerWeight;
    private double criticalRateWeight;
    private double spellCriticalRateWeight;
    private double hasteWeight;
    private double spellHasteWeight;

    public long getIdEquivalencePointSystem() {
        return idEquivalencePointSystem;
    }

    public void setIdEquivalencePointSystem(long idEquivalencePointSystem) {
        this.idEquivalencePointSystem = idEquivalencePointSystem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getIntellectWeight() {
        return intellectWeight;
    }

    public void setIntellectWeight(double intellectWeight) {
        this.intellectWeight = intellectWeight;
    }

    public double getStaminaWeight() {
        return staminaWeight;
    }

    public void setStaminaWeight(double staminaWeight) {
        this.staminaWeight = staminaWeight;
    }

    public double getAgilityWeight() {
        return agilityWeight;
    }

    public void setAgilityWeight(double agilityWeight) {
        this.agilityWeight = agilityWeight;
    }

    public double getStrengthWeight() {
        return strengthWeight;
    }

    public void setStrengthWeight(double strengthWeight) {
        this.strengthWeight = strengthWeight;
    }

    public double getSpiritWeight() {
        return spiritWeight;
    }

    public void setSpiritWeight(double spiritWeight) {
        this.spiritWeight = spiritWeight;
    }

    public double getMp5Weight() {
        return mp5Weight;
    }

    public void setMp5Weight(double mp5Weight) {
        this.mp5Weight = mp5Weight;
    }
    public double getAttackPowerWeight() {
        return attackPowerWeight;
    }

    public void setAttackPowerWeight(double attackPowerWeight) {
        this.attackPowerWeight = attackPowerWeight;
    }

    public double getHealingPowerWeight() {
        return healingPowerWeight;
    }

    public void setHealingPowerWeight(double healingPowerWeight) {
        this.healingPowerWeight = healingPowerWeight;
    }

    public double getSpellPowerWeight() {
        return spellPowerWeight;
    }

    public void setSpellPowerWeight(double spellPowerWeight) {
        this.spellPowerWeight = spellPowerWeight;
    }

    public double getCriticalRateWeight() {
        return criticalRateWeight;
    }

    public void setCriticalRateWeight(double criticalRateWeight) {
        this.criticalRateWeight = criticalRateWeight;
    }

    public double getSpellCriticalRateWeight() {
        return spellCriticalRateWeight;
    }

    public void setSpellCriticalRateWeight(double spellCriticalRateWeight) {
        this.spellCriticalRateWeight = spellCriticalRateWeight;
    }

    public double getHasteWeight() {
        return hasteWeight;
    }

    public void setHasteWeight(double hasteWeight) {
        this.hasteWeight = hasteWeight;
    }

    public double getSpellHasteWeight() {
        return spellHasteWeight;
    }

    public void setSpellHasteWeight(double spellHasteWeight) {
        this.spellHasteWeight = spellHasteWeight;
    }
}
