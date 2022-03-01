package com.riemannroch.wowsetup.model;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "TB_OWNED")
public class OwnedModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idOwned;

    private CharacterModel characterModel;

    private ItemModel itemModel;

    public long getIdOwned() {
        return idOwned;
    }

    public void setIdOwned(long idOwned) {
        this.idOwned = idOwned;
    }

    public CharacterModel getCharacterModel() {
        return characterModel;
    }

    public void setCharacterModel(CharacterModel characterModel) {
        this.characterModel = characterModel;
    }

    public ItemModel getItemModel() {
        return itemModel;
    }

    public void setItemModel(ItemModel itemModel) {
        this.itemModel = itemModel;
    }
}
