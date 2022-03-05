package com.riemannroch.wowsetup.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemEquivalencePointsKey implements Serializable {

    @Column(name = "item_id")
    Long itemId;

    @Column(name = "eps_id")
    Long epsId;

    public ItemEquivalencePointsKey(Long itemId, Long epsId) {
        this.itemId = itemId;
        this.epsId = epsId;
    }

    public ItemEquivalencePointsKey() {
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getEpsId() {
        return epsId;
    }

    public void setEpsId(Long epsId) {
        this.epsId = epsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEquivalencePointsKey that = (ItemEquivalencePointsKey) o;
        return Objects.equals(itemId, that.itemId) && Objects.equals(epsId, that.epsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, epsId);
    }
}
