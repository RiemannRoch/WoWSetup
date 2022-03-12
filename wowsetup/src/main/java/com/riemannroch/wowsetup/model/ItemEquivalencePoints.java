package com.riemannroch.wowsetup.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_ITEM_EP")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ItemEquivalencePoints implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    ItemEquivalencePointsKey id;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @MapsId("epsId")
    @JoinColumn(name = "eps_id")
    private EquivalencePointSystem eps;

    private double equivalencePoints;

    public ItemEquivalencePoints(Item item, EquivalencePointSystem eps) {
        this.id = new ItemEquivalencePointsKey(item.getIdItem(),eps.getIdEquivalencePointSystem());
        this.item = item;
        this.eps = eps;
        this.equivalencePoints = item.equivalencePoints(eps);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ItemEquivalencePoints that = (ItemEquivalencePoints) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
