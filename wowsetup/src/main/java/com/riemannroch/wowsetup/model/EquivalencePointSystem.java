package com.riemannroch.wowsetup.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_EQUIVALENCEPOINTSYSTEM")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class EquivalencePointSystem implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EquivalencePointSystem that = (EquivalencePointSystem) o;
        return idEquivalencePointSystem != 0 && Objects.equals(idEquivalencePointSystem, that.idEquivalencePointSystem);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
