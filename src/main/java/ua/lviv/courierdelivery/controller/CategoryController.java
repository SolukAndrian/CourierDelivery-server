package ua.lviv.courierdelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.lviv.courierdelivery.model.dto.DTOBuilder;
import ua.lviv.courierdelivery.model.dto.impl.CategoryDTO;
import ua.lviv.courierdelivery.model.dto.impl.CategoryLinkDTO;
import ua.lviv.courierdelivery.model.entity.Category;
import ua.lviv.courierdelivery.service.CategoryService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/api/admin/add/category")
    public ResponseEntity<CategoryLinkDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService
                .addCategory(categoryDTO.getName());
        Link selfLink = linkTo(methodOn(CategoryController.class).getCategoryById(category.getId())).withSelfRel();
        CategoryLinkDTO categoryPublicDTO = DTOBuilder.buildDtoForEntity(category, CategoryLinkDTO.class, selfLink);
        return new ResponseEntity<>(categoryPublicDTO, HttpStatus.CREATED);
    }


    @GetMapping(value = "/api/category/findAll")
    public ResponseEntity<List<CategoryLinkDTO>> getAllCategories() {
        List<Category> categoryList = categoryService.findAll();
        Link collectionLink = linkTo(methodOn(CategoryController.class).getAllCategories()).withSelfRel();
        List<CategoryLinkDTO> categories = DTOBuilder
                .buildDtoListForCollection(categoryList, CategoryLinkDTO.class, collectionLink);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/api/category/{id}")
    public ResponseEntity<CategoryLinkDTO> getCategoryById(@PathVariable Integer id) {
        Category category = categoryService.findOne(id);
        Link selfLink = linkTo(methodOn(CategoryController.class).getCategoryById(category.getId())).withRel("category");
        CategoryLinkDTO publicDTO = DTOBuilder.buildDtoForEntity(category, CategoryLinkDTO.class, selfLink);
        return new ResponseEntity<>(publicDTO, HttpStatus.OK);
    }

    @PutMapping("/api/admin/update/category/{id}")
    public ResponseEntity<CategoryLinkDTO> updateCategory(@RequestBody CategoryDTO categoryDTO,
                                                          @PathVariable Integer id) {
        Category category = categoryService.updateCategory(id, Category.toEntity(categoryDTO));
        Link selfLink = linkTo(methodOn(CategoryController.class).getCategoryById(category.getId())).withSelfRel();
        CategoryLinkDTO categoryLinkDTO = DTOBuilder.buildDtoForEntity(category, CategoryLinkDTO.class, selfLink);
        return new ResponseEntity<>(categoryLinkDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/admin/delete/category/{cardId}")
    public void deleteCategory(@PathVariable Integer cardId) {
        categoryService.delete(cardId);
    }

}
