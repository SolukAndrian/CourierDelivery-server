package ua.lviv.courierdelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.lviv.courierdelivery.model.dto.DTOBuilder;
import ua.lviv.courierdelivery.model.dto.impl.ImageDTO;
import ua.lviv.courierdelivery.model.dto.impl.UploadingImageDTO;
import ua.lviv.courierdelivery.model.entity.Image;
import ua.lviv.courierdelivery.repository.ImageRepository;
import ua.lviv.courierdelivery.service.ImageService;
import ua.lviv.courierdelivery.utils.exception.CanNotBeDeletedException;
import ua.lviv.courierdelivery.utils.exception.ImageRepositorySizeQuotaExceededException;
import ua.lviv.courierdelivery.utils.exception.NotAuthorisedUserException;
import ua.lviv.courierdelivery.utils.exception.NotOwnerOperationException;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Apple on 08.01.2018.
 */
@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageRepository imageRepository;

    @PostMapping("/api/service/image/addImageToDB")
    public ResponseEntity<UploadingImageDTO> addImageToDB(@RequestParam("file") MultipartFile file)
            throws ImageRepositorySizeQuotaExceededException, NotAuthorisedUserException {
        Image image = imageService.addImageToDB(file);
        Integer imageId = image.getId();
        Link link = linkTo(methodOn(ImageController.class).getImageById(imageId)).withSelfRel();
        UploadingImageDTO uploadingimageDTO = DTOBuilder.buildDtoForEntity(image, UploadingImageDTO.class, link);
        Integer bytesLeft = imageService.getUsersLimitInBytesForImagesLeft(image.getCreatedBy().getId());
        uploadingimageDTO.setBytesLeft(bytesLeft);
        return new ResponseEntity<>(uploadingimageDTO, HttpStatus.OK);
    }

    @GetMapping("/api/service/images/user")
    public ResponseEntity<List<ImageDTO>> getAllImagesByUserId() throws NotAuthorisedUserException {
        List<Image> listId = imageService.getImagesForCurrentUser();
        Link link = linkTo(methodOn(ImageController.class).getImageList()).withSelfRel();
        List<ImageDTO> imageDTOList = DTOBuilder.buildDtoListForCollection(listId, ImageDTO.class, link);
        return new ResponseEntity<>(imageDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/api/service/image/{id}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<byte[]> getImageById(@PathVariable("id") Integer id) {
        byte[] imageContentBytes = imageService.getDecodedImageContentByImageId(id);
        if (imageContentBytes == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageContentBytes);
    }

    @GetMapping(value = "/api/admin/service/image")
    public ResponseEntity<List<ImageDTO>> getImageList() {
        List<Image> listId = imageRepository.getImagesWithoutContent();
        Link link = linkTo(methodOn(ImageController.class).getImageList()).withSelfRel();
        List<ImageDTO> imageDTOList = DTOBuilder.buildDtoListForCollection(listId, ImageDTO.class, link);
        return new ResponseEntity<>(imageDTOList, HttpStatus.OK);
    }


    @DeleteMapping(value = "/api/service/image/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") Integer id) throws CanNotBeDeletedException,
            NotOwnerOperationException, NotAuthorisedUserException {
        imageService.deleteImage(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
