package com.riemannroch.wowsetup.model;

import com.riemannroch.wowsetup.request.CharacterRequest;
import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TB_CHARACTER")
@Data
public class CharacterModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idCharacter;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "character_owns_item",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<ItemModel> itemsList;

    public CharacterModel(){}

    public CharacterModel(CharacterRequest characterRequest){
        this.name = characterRequest.getName();
    }
}
