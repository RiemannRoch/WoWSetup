package com.riemannroch.wowsetup.request;

import com.riemannroch.wowsetup.model.SlotEnum;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ItemRequest {
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
}
