package ua.lviv.courierdelivery.service;

import org.springframework.web.multipart.MultipartFile;
import ua.lviv.courierdelivery.model.entity.Image;
import ua.lviv.courierdelivery.utils.exception.CanNotBeDeletedException;
import ua.lviv.courierdelivery.utils.exception.ImageRepositorySizeQuotaExceededException;
import ua.lviv.courierdelivery.utils.exception.NotAuthorisedUserException;
import ua.lviv.courierdelivery.utils.exception.NotOwnerOperationException;

import java.util.List;

/**
 * Created by Apple on 08.01.2018.
 */
public interface ImageService {
    Image addImageToDB(MultipartFile file) throws ImageRepositorySizeQuotaExceededException, NotAuthorisedUserException;

    void checkImageExtention(MultipartFile file) throws ImageRepositorySizeQuotaExceededException,
            NotAuthorisedUserException;

    byte[] getDecodedImageContentByImageId(Integer id);

    String encodeToBase64(MultipartFile file);

    byte[] decodeFromBase64(String encodedFileContent);

    Integer getUsersLimitInBytesForImagesLeft(Integer userId);

    void deleteImage(Integer id) throws CanNotBeDeletedException, NotOwnerOperationException, NotAuthorisedUserException;

    void setImageStatusInUse(Integer imageId);

    void setImageStatusNotInUse(Integer imageId);

    List<Image> getImagesForCurrentUser() throws NotAuthorisedUserException;
}
