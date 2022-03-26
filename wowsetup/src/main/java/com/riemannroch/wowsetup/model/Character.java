package com.riemannroch.wowsetup.model;

import com.riemannroch.wowsetup.request.CharacterRequest;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_CHARACTER")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Character implements Serializable {
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
    @ToString.Exclude
    private List<Item> itemsList;

    public Character(CharacterRequest characterRequest){
        this.name = characterRequest.getName();
        this.itemsList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Character character = (Character) o;
        return idCharacter != 0 && Objects.equals(idCharacter, character.idCharacter);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
