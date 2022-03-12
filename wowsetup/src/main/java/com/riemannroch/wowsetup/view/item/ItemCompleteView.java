package com.riemannroch.wowsetup.view.item;

import com.riemannroch.wowsetup.model.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public ItemCompleteView(Item item){
        super(item);
        this.intellect = item.getIntellect();
        this.stamina = item.getStamina();
        this.agility = item.getAgility();
        this.strength = item.getStrength();
        this.spirit = item.getSpirit();
        this.mp5 = item.getMp5();
        this.attackPower = item.getAttackPower();
        this.healingPower = item.getHealingPower();
        this.spellPower = item.getSpellPower();
        this.criticalRate = item.getCriticalRate();
        this.spellCriticalRate = item.getSpellCriticalRate();
        this.haste = item.getHaste();
        this.spellHaste = item.getSpellHaste();
        this.resilience = item.getResilience();
    }
}
