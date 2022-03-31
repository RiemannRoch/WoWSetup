package com.riemannroch.wowsetup.model;

import com.riemannroch.wowsetup.request.ItemRequest;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
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
    private int hit;
    private int spellHit;

    @ManyToMany(mappedBy = "itemsList")
    @ToString.Exclude
    private List<Character> ownersList;

    public Item(ItemRequest itemRequest) {
        this.name = itemRequest.getName();
        this.slotEnum =  itemRequest.getSlotEnum();
        this.intellect = itemRequest.getIntellect();
        this.stamina = itemRequest.getStamina();
        this.agility = itemRequest.getAgility();
        this.strength = itemRequest.getStrength();
        this.spirit = itemRequest.getSpirit();
        this.mp5 = itemRequest.getMp5();
        this.attackPower = itemRequest.getAttackPower();
        this.healingPower = itemRequest.getHealingPower();
        this.spellPower = itemRequest.getSpellPower();
        this.criticalRate = itemRequest.getCriticalRate();
        this.spellCriticalRate = itemRequest.getSpellCriticalRate();
        this.haste = itemRequest.getHaste();
        this.spellHaste = itemRequest.getSpellHaste();
        this.resilience = itemRequest.getResilience();
        this.hit = itemRequest.getHit();
        this.spellHit = itemRequest.getSpellHit();
        this.ownersList = new ArrayList<>();
    }

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
