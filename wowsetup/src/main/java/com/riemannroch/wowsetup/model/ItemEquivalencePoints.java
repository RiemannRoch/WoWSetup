package com.riemannroch.wowsetup.model;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "TB_ITEM_EP")
public class ItemEquivalencePoints implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    ItemEquivalencePointsKey id;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private ItemModel item;

    @ManyToOne
    @MapsId("epsId")
    @JoinColumn(name = "eps_id")
    private EquivalencePointSystemModel eps;

    private double equivalencePoints;

    public ItemEquivalencePoints(ItemModel item, EquivalencePointSystemModel eps) {
        this.id = new ItemEquivalencePointsKey(item.getIdItem(),eps.getIdEquivalencePointSystem());
        this.item = item;
        this.eps = eps;
        this.equivalencePoints = item.equivalencePoints(eps);
    }

    public ItemEquivalencePoints() {
    }

    public ItemEquivalencePointsKey getId() {
        return id;
    }

    public void setId(ItemEquivalencePointsKey id) {
        this.id = id;
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
        this.equivalencePoints = this.item.equivalencePoints(this.eps);
    }

    public EquivalencePointSystemModel getEps() {
        return eps;
    }

    public void setEps(EquivalencePointSystemModel eps) {
        this.eps = eps;
        this.equivalencePoints = this.item.equivalencePoints(this.eps);
    }

    public double getEquivalencePoints() {
        return equivalencePoints;
    }

    /* public void setEquivalencePoints(double equivalencePoints) {
        this.equivalencePoints = equivalencePoints;
    }*/
}
