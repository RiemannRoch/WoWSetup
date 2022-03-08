package com.riemannroch.wowsetup.control;

import com.riemannroch.wowsetup.model.CharacterModel;
import com.riemannroch.wowsetup.model.EquivalencePointSystemModel;
import com.riemannroch.wowsetup.model.ItemModel;
import com.riemannroch.wowsetup.model.SlotEnum;
import com.riemannroch.wowsetup.service.CharacterService;
import com.riemannroch.wowsetup.service.EquivalencePointSystemService;
import com.riemannroch.wowsetup.service.ItemService;
import com.riemannroch.wowsetup.view.character.CharacterView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wowsetup/{name}/bis")
public class BestInSlotController {
    final CharacterService characterService;
    final ItemService itemService;
    final EquivalencePointSystemService equivalencePointSystemService;

    public BestInSlotController(CharacterService characterService, ItemService itemService,
                                EquivalencePointSystemService equivalencePointSystemService) {
        this.characterService = characterService;
        this.itemService = itemService;
        this.equivalencePointSystemService = equivalencePointSystemService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllEquivalencePointSystemsForCharacter(@PathVariable("name") String name) {
        Optional<CharacterModel> characterModelOptional = characterService.findByName(name);
        if (characterModelOptional.isEmpty()) {
            return CharacterController.notFound();
        }
        List<Object> response = new ArrayList<>();
        response.add(new CharacterView(characterModelOptional.get()));
        response.add(equivalencePointSystemService.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{idEquivalencePointSystem}")
    public ResponseEntity<Object> getBestInSlotList(
            @PathVariable("name") String name,
            @PathVariable("idEquivalencePointSystem") long idEquivalencePointSystem) {
        Optional<CharacterModel> characterModelOptional = characterService.findByName(name);
        if (characterModelOptional.isEmpty()) {
            return CharacterController.notFound();
        }
        Optional<EquivalencePointSystemModel> equivalencePointSystemModelOptional =
                equivalencePointSystemService.findById(idEquivalencePointSystem);
        if (equivalencePointSystemModelOptional.isEmpty()) {
            return EquivalencePointSystemController.notFound();
        }
        CharacterModel character = characterModelOptional.get();
        EquivalencePointSystemModel eps = equivalencePointSystemModelOptional.get();

        List<ItemModel> bestInSlotList = new ArrayList<>();

        for (SlotEnum slotEnum : SlotEnum.values()) {
            List<ItemModel> itemsInSlot = itemService.findBySlot(slotEnum);
            ItemModel bestInSlot = new ItemModel();
            bestInSlot.setSlotEnum(slotEnum);
            ItemModel secondBestInSlot = new ItemModel();
            secondBestInSlot.setSlotEnum(slotEnum);
            double bestInSlotEquivalencePoints = 0;
            for (ItemModel item : itemsInSlot) {
                if (item.equivalencePoints(eps) > bestInSlotEquivalencePoints) {
                    secondBestInSlot = bestInSlot;
                    bestInSlot = item;
                    bestInSlotEquivalencePoints = item.equivalencePoints(eps);
                }
            }
            bestInSlotList.add(bestInSlot);
            if (slotEnum == SlotEnum.RING || slotEnum == SlotEnum.TRINKET){
                bestInSlotList.add(secondBestInSlot);
            }
        }

        List<Object> response = new ArrayList<>();
        response.add(character);
        response.add(eps);
        response.add(bestInSlotList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
