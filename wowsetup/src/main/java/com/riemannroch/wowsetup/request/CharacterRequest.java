package com.riemannroch.wowsetup.request;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CharacterRequest {
    private String name;
}
