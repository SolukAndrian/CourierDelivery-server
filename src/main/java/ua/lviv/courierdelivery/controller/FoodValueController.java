package ua.lviv.courierdelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.courierdelivery.model.dto.DTOBuilder;
import ua.lviv.courierdelivery.model.dto.impl.FoodValueDTO;
import ua.lviv.courierdelivery.model.dto.impl.FoodValueLinkDTO;
import ua.lviv.courierdelivery.model.entity.FoodValue;
import ua.lviv.courierdelivery.service.FoodValueService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Apple on 08.01.2018.
 */
@RestController
public class FoodValueController {
    @Autowired
    private FoodValueService foodValueService;

    @PostMapping("/api/admin/add/foodValue")
    public ResponseEntity<FoodValueLinkDTO> addFoodValue(@RequestBody FoodValueDTO foodValueDTO) {
        FoodValue foodValue = foodValueService
                .addFoodValue(new FoodValue(foodValueDTO.getProteins(), foodValueDTO.getFats(), foodValueDTO.getCarbohydrates(), foodValueDTO.getCal()), foodValueDTO.getProductId());
        Link selfLink = linkTo(methodOn(FoodValueController.class).getFoodValueById(foodValue.getId())).withSelfRel();
        FoodValueLinkDTO categoryPublicDTO = DTOBuilder.buildDtoForEntity(foodValue, FoodValueLinkDTO.class, selfLink);
        return new ResponseEntity<>(categoryPublicDTO, HttpStatus.CREATED);
    }


    @GetMapping(value = "/api/foodValue/findAll")
    public ResponseEntity<List<FoodValueLinkDTO>> getAllFoodValues() {
        List<FoodValue> foodValueList = foodValueService.findAll();
        Link collectionLink = linkTo(methodOn(FoodValueController.class).getAllFoodValues()).withSelfRel();
        List<FoodValueLinkDTO> foodValueLinkDTOS = DTOBuilder
                .buildDtoListForCollection(foodValueList, FoodValueLinkDTO.class, collectionLink);
        return new ResponseEntity<>(foodValueLinkDTOS, HttpStatus.OK);
    }

    @GetMapping("/api/foodValue/{id}")
    public ResponseEntity<FoodValueLinkDTO> getFoodValueById(@PathVariable Integer id) {
        FoodValue foodValue = foodValueService.findOne(id);
        Link selfLink = linkTo(methodOn(FoodValueController.class).getFoodValueById(foodValue.getId())).withRel("foodValue");
        FoodValueLinkDTO foodValueLinkDTO = DTOBuilder.buildDtoForEntity(foodValue, FoodValueLinkDTO.class, selfLink);
        return new ResponseEntity<>(foodValueLinkDTO, HttpStatus.OK);
    }

    @PutMapping("/api/admin/update/foodValue/{id}")
    public ResponseEntity<FoodValueLinkDTO> updateFoodValue(@RequestBody FoodValueDTO categoryDTO,
                                                            @PathVariable Integer id) {
        FoodValue foodValue = foodValueService.updateFoodValue(id, FoodValue.toEntity(categoryDTO));
        Link selfLink = linkTo(methodOn(FoodValueController.class).getFoodValueById(foodValue.getId())).withSelfRel();
        FoodValueLinkDTO foodValueLinkDTO = DTOBuilder.buildDtoForEntity(foodValue, FoodValueLinkDTO.class, selfLink);
        return new ResponseEntity<>(foodValueLinkDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/admin/delete/foodValue/{foodValueId}")
    public void deleteFoodValue(@PathVariable Integer foodValue) {
        foodValueService.delete(foodValue);
    }
}
