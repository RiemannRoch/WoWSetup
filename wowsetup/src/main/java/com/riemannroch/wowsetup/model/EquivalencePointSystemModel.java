package com.riemannroch.wowsetup.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "TB_EQUIVALENCEPOINTSYSTEM")
@Data
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
}
