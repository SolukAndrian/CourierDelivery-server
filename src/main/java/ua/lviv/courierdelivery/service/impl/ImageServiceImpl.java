package ua.lviv.courierdelivery.service.impl;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import ua.lviv.courierdelivery.model.entity.Image;
import ua.lviv.courierdelivery.model.entity.User;
import ua.lviv.courierdelivery.repository.ImageRepository;
import ua.lviv.courierdelivery.service.ImageService;
import ua.lviv.courierdelivery.service.UserService;
import ua.lviv.courierdelivery.utils.exception.CanNotBeDeletedException;
import ua.lviv.courierdelivery.utils.exception.ImageRepositorySizeQuotaExceededException;
import ua.lviv.courierdelivery.utils.exception.NotAuthorisedUserException;
import ua.lviv.courierdelivery.utils.exception.NotOwnerOperationException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Apple on 08.01.2018.
 */
@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private UserService userService;
    @Autowired
    private ImageRepository imageRepository;
    @Value("${app.images.maxSize}")
    private Integer maxFileSize;
    @Value("${app.images.userQuote}")
    private Integer userQuote;

    @Override
    public Image addImageToDB(MultipartFile file)
            throws ImageRepositorySizeQuotaExceededException, NotAuthorisedUserException {
        checkImageExtention(file);
        Image image = new Image(encodeToBase64(file), file.getContentType(),
                userService.getAuthorizedUser(), file.getSize());
        imageRepository.save(image);
        image = imageRepository.getImageWithoutContent(image.getId());
        return image;
    }


    @Override
    public void checkImageExtention(MultipartFile file) throws ImageRepositorySizeQuotaExceededException,
            NotAuthorisedUserException {
        long fileSize = file.getSize();
        User user = userService.getAuthorizedUser();
        if (fileSize > getUsersLimitInBytesForImagesLeft(user.getId())) {
            throw new ImageRepositorySizeQuotaExceededException();
        }
        if (fileSize > maxFileSize) {
            throw new MultipartException("File upload error: file is too large.");
        } else {
            String imageType = file.getContentType();
            if (imageType == null || !imageType.split("/")[0].equalsIgnoreCase("image")) {
                throw new IllegalArgumentException("File upload error: file is not an image");
            }
        }
    }

    @Override
    public byte[] getDecodedImageContentByImageId(Integer id) {
        byte[] imageContent = null;
        List<Integer> idList = imageRepository.getIdList();
        for (Integer existingId : idList) {
            if (id.equals(existingId)) {
                Image image = imageRepository.findImageById(id);
                String encodedFileContent = image.getImagebase64();
                imageContent = decodeFromBase64(encodedFileContent);
                break;
            }
        }
        return imageContent;
    }

    @Override
    public String encodeToBase64(MultipartFile file) {
        String encodedFile = null;
        byte[] bytes = new byte[(int) file.getSize()];
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        encodedFile = Base64.encodeBase64String(bytes);
        return encodedFile;
    }

    @Override
    public byte[] decodeFromBase64(String encodedFileContent) {

        return Base64.decodeBase64(encodedFileContent);
    }

    @Override
    public Integer getUsersLimitInBytesForImagesLeft(Integer userId) {

        Integer bytesUsed = imageRepository.getSumOfImagesSizesOfUserById(userId);
        if (bytesUsed == null) {
            bytesUsed = 0;
        }
        Integer bytesLeft = userQuote - bytesUsed;
        return bytesLeft;
    }

    @Override
    public void deleteImage(Integer id)
            throws CanNotBeDeletedException, NotOwnerOperationException, NotAuthorisedUserException {
        Image image = imageRepository.findImageById(id);
        Integer imageOwnerId = image.getCreatedBy().getId();
        Integer userId = 0;
        userId = userService.getAuthorizedUser().getId();
        if (imageOwnerId != userId) {
            throw new NotOwnerOperationException();
        }
        boolean isUsed = image.getIsImageUsed();
        if (isUsed) {
            throw new CanNotBeDeletedException();
        } else {
            imageRepository.delete(image);
        }
    }

    @Override
    public void setImageStatusInUse(Integer imageId) {
        Image image = imageRepository.findOne(imageId);
        image.setIsImageUsed(true);
        imageRepository.save(image);
    }

    @Override
    public void setImageStatusNotInUse(Integer imageId) {
        Image image = imageRepository.findOne(imageId);
        image.setIsImageUsed(false);
        imageRepository.save(image);
    }

    @Override
    public List<Image> getImagesForCurrentUser() throws NotAuthorisedUserException {
        Integer userId = userService.getAuthorizedUser().getId();
        return imageRepository.getImagesWithoutContentById(userId);
    }
}
