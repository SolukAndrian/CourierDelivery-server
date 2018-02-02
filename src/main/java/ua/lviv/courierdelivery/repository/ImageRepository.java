package ua.lviv.courierdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.lviv.courierdelivery.model.entity.Image;

import java.util.List;

/**
 * Created by Apple on 08.01.2018.
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    public Image findImageById(Integer id);

    @Query("SELECT id FROM Image")
    public List<Integer> getIdList();

    @Query("SELECT i.id FROM Image i")
    public List<Image> getImagesWithoutContent();

    @Query("SELECT i.id, i.isImageUsed FROM Image i JOIN i.createdBy u WHERE u.id = ?1")
    public List<Image> getImagesWithoutContentById(Integer id);

    @Query("SELECT i.id, i.createdBy FROM Image i WHERE i.id = ?1")
    public Image getImageWithoutContent(Integer id);

    @Query("SELECT SUM(i.size) FROM Image i WHERE user_id = ?1")
    public Integer getSumOfImagesSizesOfUserById(Integer id);
}
