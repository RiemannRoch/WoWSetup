package com.riemannroch.wowsetup.control;

import com.riemannroch.wowsetup.model.*;
import com.riemannroch.wowsetup.model.Character;
import com.riemannroch.wowsetup.service.*;
import com.riemannroch.wowsetup.view.item.ItemView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/wowsetup/bis/{name}/{idEps}")
public class BestInSlotController {
    private final EquivalencePointSystemService equivalencePointSystemService;
    private final CharacterService characterService;
    private final ItemService itemService;
    private final ItemEquivalencePointsService itemEquivalencePointsService;

    //Tested
    @GetMapping
    public List<ItemView> getBisList(@PathVariable String name, @PathVariable Long idEps, @RequestParam(defaultValue = "false") boolean all) {
        Character character = characterService.findByName(name)
                .orElseThrow(() -> CharacterController.notFound(name));
        EquivalencePointSystem eps = equivalencePointSystemService.findById(idEps)
                .orElseThrow(() -> EquivalencePointSystemController.notFound(idEps));

        List<Item> list = all ? itemService.findAll() : character.getItemsList();

        List<Item> bisList = new ArrayList<>();
        for (SlotEnum slot : SlotEnum.values()) {
            List<Item> itemsForSlot = new ArrayList<>();
            for (Item item : list) {
                if (item.getSlotEnum().equals(slot)) {
                    itemsForSlot.add(item);
                }
            }
            Item bisItem = new Item();
            bisItem.setSlotEnum(slot);
            double bisEp = 0;
            for (Item item : itemsForSlot) {
                assert itemEquivalencePointsService
                        .findById(new ItemEquivalencePointsKey(item.getIdItem(), eps.getIdEquivalencePointSystem()))
                        .isPresent();
                double ep = itemEquivalencePointsService
                        .findById(new ItemEquivalencePointsKey(item.getIdItem(), eps.getIdEquivalencePointSystem()))
                        .get()
                        .getEquivalencePoints();
                if (ep > bisEp) {
                    bisItem = item;
                }
            }
            bisList.add(bisItem);
        }
        return ItemView.listOf(bisList);
    }

    //Tested
    @GetMapping("/{slot}")
    public List<ItemView> getBisListForSlot(
            @PathVariable String name,
            @PathVariable Long idEps,
            @PathVariable SlotEnum slot,
            @RequestParam(defaultValue = "false") boolean all) {
        Character character = characterService.findByName(name)
                .orElseThrow(() -> CharacterController.notFound(name));
        EquivalencePointSystem eps = equivalencePointSystemService.findById(idEps)
                .orElseThrow(() -> EquivalencePointSystemController.notFound(idEps));

        List<ItemEquivalencePoints> itemEquivalencePointsList = itemEquivalencePointsService.findByEpsAndOrderByEquivalencePoints(eps);

        List<Item> bisList = new ArrayList<>();

        for (ItemEquivalencePoints itemEquivalencePoints : itemEquivalencePointsList) {
            Item item = itemEquivalencePoints.getItem();
            if (item.getSlotEnum().equals(slot)) {
                if (all || character.getItemsList().contains(item)) bisList.add(item);
            }
        }

        return ItemView.listOf(bisList);
    }
}
