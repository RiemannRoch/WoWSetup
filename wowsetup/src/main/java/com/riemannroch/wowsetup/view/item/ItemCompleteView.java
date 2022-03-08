package com.riemannroch.wowsetup.view.item;

import com.riemannroch.wowsetup.model.ItemModel;
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
}
