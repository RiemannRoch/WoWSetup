package com.riemannroch.wowsetup.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_ITEM")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Item implements Serializable {
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
    @ToString.Exclude
    private List<Character> ownersList;

    public double equivalencePoints(EquivalencePointSystem equivalencePointSystem){
        return intellect * equivalencePointSystem.getIntellectWeight() +
                stamina * equivalencePointSystem.getStaminaWeight() +
                agility * equivalencePointSystem.getAgilityWeight() +
                strength * equivalencePointSystem.getStrengthWeight() +
                spirit * equivalencePointSystem.getSpiritWeight() +
                mp5 * equivalencePointSystem.getMp5Weight() +
                attackPower * equivalencePointSystem.getAttackPowerWeight() +
                healingPower * equivalencePointSystem.getHealingPowerWeight() +
                spellPower * equivalencePointSystem.getSpellPowerWeight() +
                criticalRate * equivalencePointSystem.getCriticalRateWeight() +
                spellCriticalRate * equivalencePointSystem.getSpellCriticalRateWeight() +
                haste * equivalencePointSystem.getHasteWeight() +
                spellHaste * equivalencePointSystem.getSpellHasteWeight();
    }

    public List<Character> getOwnersList() {
        return ownersList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Item item = (Item) o;
        return idItem != 0 && Objects.equals(idItem, item.idItem);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
