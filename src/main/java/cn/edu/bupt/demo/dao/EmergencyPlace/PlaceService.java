package cn.edu.bupt.demo.dao.EmergencyPlace;

import cn.edu.bupt.demo.entity.EmergencyPlace;
import java.util.List;

public interface PlaceService {
    List<EmergencyPlace> findAllByPage(Integer page, Integer pageSize);

    List<EmergencyPlace> findPlaceByCategoryAndPage(String category,Integer page, Integer pageSize);

    Integer findPlacePageNum(Integer size);

    EmergencyPlace findPlaceById(Integer place_id);

   /* List<EmergencyPlace> findPlaceByName(String name);

    List<EmergencyPlace> findPlaceByAffiliation(String affiliation);

    List<EmergencyPlace> findPlaceByLocation(String location);*/

    Integer getPlaceCount();

    Integer PlaceCountOfCategory(String category);

    void save(EmergencyPlace emergencyPlace);

    void update(EmergencyPlace emergencyPlace);

    void deleteById(Integer place_id);

    List<EmergencyPlace> findAllPlace();
}
