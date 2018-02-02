package ua.lviv.courierdelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.courierdelivery.model.dto.DTOBuilder;
import ua.lviv.courierdelivery.model.dto.impl.ProductDTO;
import ua.lviv.courierdelivery.model.dto.impl.ProductLinkDTO;
import ua.lviv.courierdelivery.model.entity.Product;
import ua.lviv.courierdelivery.service.ProductService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Apple on 09.01.2018.
 */
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/api/admin/add/product")
    public ResponseEntity<ProductLinkDTO> addProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService
                .addProduct(new Product(productDTO.getName(), productDTO.getPrice(), productDTO.getDescription(), productDTO.getWeightProduct()), productDTO.getCategoryId());
        Link selfLink = linkTo(methodOn(ProductController.class).getProductById(product.getId())).withSelfRel();
        ProductLinkDTO productLinkDTO = DTOBuilder.buildDtoForEntity(product, ProductLinkDTO.class, selfLink);
        return new ResponseEntity<>(productLinkDTO, HttpStatus.CREATED);
    }


    @GetMapping(value = "/api/product/findAll")
    public ResponseEntity<List<ProductLinkDTO>> getAllProducts() {
        List<Product> productList = productService.findAll();
        Link collectionLink = linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel();
        List<ProductLinkDTO> linkDTOList = DTOBuilder
                .buildDtoListForCollection(productList, ProductLinkDTO.class, collectionLink);
        return new ResponseEntity<>(linkDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/api/category/{categoryId}/findAllProducts")
    public ResponseEntity<List<ProductLinkDTO>> getAllProductsByCategoryId(@PathVariable Integer categoryId) {
        List<Product> productList = productService.findAllByCategoryId(categoryId);
        Link collectionLink = linkTo(methodOn(ProductController.class).getAllProductsByCategoryId(categoryId)).withSelfRel();
        List<ProductLinkDTO> linkDTOList = DTOBuilder
                .buildDtoListForCollection(productList, ProductLinkDTO.class, collectionLink);
        return new ResponseEntity<>(linkDTOList, HttpStatus.OK);
    }

    @GetMapping("/api/product/{id}")
    public ResponseEntity<ProductLinkDTO> getProductById(@PathVariable Integer id) {
        Product product = productService.findOne(id);
        Link selfLink = linkTo(methodOn(ProductController.class).getProductById(product.getId())).withRel("category");
        ProductLinkDTO productLinkDTO = DTOBuilder.buildDtoForEntity(product, ProductLinkDTO.class, selfLink);
        return new ResponseEntity<>(productLinkDTO, HttpStatus.OK);
    }

    @PutMapping("/api/admin/update/product/{id}")
    public ResponseEntity<ProductLinkDTO> updateProduct(@RequestBody ProductDTO categoryDTO,
                                                        @PathVariable Integer id) {
        Product category = productService.updateProduct(id, Product.toEntity(categoryDTO));
        Link selfLink = linkTo(methodOn(ProductController.class).getProductById(category.getId())).withSelfRel();
        ProductLinkDTO categoryLinkDTO = DTOBuilder.buildDtoForEntity(category, ProductLinkDTO.class, selfLink);
        return new ResponseEntity<>(categoryLinkDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/admin/delete/product/{productId}")
    public void deleteProduct(@PathVariable Integer productId) {
        productService.delete(productId);
    }
}
