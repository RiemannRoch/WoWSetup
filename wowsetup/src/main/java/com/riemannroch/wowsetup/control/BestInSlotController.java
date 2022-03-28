package com.riemannroch.wowsetup.control;

import com.riemannroch.wowsetup.model.Character;
import com.riemannroch.wowsetup.model.*;
import com.riemannroch.wowsetup.service.CharacterService;
import com.riemannroch.wowsetup.service.EquivalencePointSystemService;
import com.riemannroch.wowsetup.service.ItemEquivalencePointsService;
import com.riemannroch.wowsetup.service.ItemService;
import com.riemannroch.wowsetup.view.itemequivalencepoints.ItemEquivalencePointsView;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Return the Best in Slot List for a given character and eps")
    @GetMapping
    public List<ItemEquivalencePointsView> getBisList(@PathVariable String name, @PathVariable Long idEps, @RequestParam(defaultValue = "false") boolean all) {
        Character character = characterService.findByName(name)
                .orElseThrow(() -> CharacterController.notFound(name));
        EquivalencePointSystem eps = equivalencePointSystemService.findById(idEps)
                .orElseThrow(() -> EquivalencePointSystemController.notFound(idEps));

        List<Item> source = all ? itemService.findAll() : character.getItemsList();

        List<ItemEquivalencePoints> bisList = new ArrayList<>();
        for (SlotEnum slot : SlotEnum.values()) {
            List<Item> itemsForSlot = new ArrayList<>();
            for (Item item : source) {
                if (item.getSlotEnum().equals(slot)) {
                    itemsForSlot.add(item);
                }
            }
            ItemEquivalencePoints bisItem = new ItemEquivalencePoints();
            Item noSlotItem = new Item();
            noSlotItem.setSlotEnum(slot);
            bisItem.setItem(noSlotItem);
            double bisEp = 0;
            for (Item item : itemsForSlot) {
                assert itemEquivalencePointsService
                        .findById(new ItemEquivalencePointsKey(item.getIdItem(), eps.getIdEquivalencePointSystem()))
                        .isPresent();

                ItemEquivalencePoints itemEquivalencePoints = itemEquivalencePointsService
                        .findById(new ItemEquivalencePointsKey(item.getIdItem(), eps.getIdEquivalencePointSystem())).get();

                double ep = itemEquivalencePoints.getEquivalencePoints();

                if (ep > bisEp) {
                    bisItem = itemEquivalencePoints;
                }
            }
            bisList.add(bisItem);
        }
        return ItemEquivalencePointsView.listOf(bisList);
    }

    //Tested
    @Operation(summary = "Return the best item given character, eps and slot")
    @GetMapping("/{slot}")
    public List<ItemEquivalencePointsView> getBisListForSlot(
            @PathVariable String name,
            @PathVariable Long idEps,
            @PathVariable SlotEnum slot,
            @RequestParam(defaultValue = "false") boolean all) {
        Character character = characterService.findByName(name)
                .orElseThrow(() -> CharacterController.notFound(name));
        EquivalencePointSystem eps = equivalencePointSystemService.findById(idEps)
                .orElseThrow(() -> EquivalencePointSystemController.notFound(idEps));

        List<ItemEquivalencePoints> itemEquivalencePointsList = itemEquivalencePointsService.findByEpsAndOrderByEquivalencePoints(eps);

        List<ItemEquivalencePoints> bisList = new ArrayList<>();

        for (ItemEquivalencePoints itemEquivalencePoints : itemEquivalencePointsList) {
            Item item = itemEquivalencePoints.getItem();
            if (itemEquivalencePoints.getItem().getSlotEnum().equals(slot)) {
                if (all || character.getItemsList().contains(item)) bisList.add(itemEquivalencePoints);
            }
        }

        return ItemEquivalencePointsView.listOf(bisList);
    }
}
