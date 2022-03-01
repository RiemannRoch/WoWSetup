package com.riemannroch.wowsetup.model;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "TB_CHARACTER")
public class CharacterModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idCharacter;

    private String name;

    public long getIdCharacter() {
        return idCharacter;
    }

    public void setIdCharacter(long idCharacter) {
        this.idCharacter = idCharacter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
