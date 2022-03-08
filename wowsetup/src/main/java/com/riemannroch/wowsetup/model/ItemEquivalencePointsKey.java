package com.riemannroch.wowsetup.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ItemEquivalencePointsKey implements Serializable {

    @Column(name = "item_id")
    Long itemId;

    @Column(name = "eps_id")
    Long epsId;

    public ItemEquivalencePointsKey(Long itemId, Long epsId) {
        this.itemId = itemId;
        this.epsId = epsId;
    }

}
