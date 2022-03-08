package com.riemannroch.wowsetup.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TB_ITEM")
@Data
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
}
