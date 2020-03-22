package cn.edu.bupt.demo.dao.EmergencyPlace;

import cn.edu.bupt.demo.entity.EmergencyPlace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    PlaceRespository placeRespository;

    @Override
    public List<EmergencyPlace> findAllByPage(Integer page, Integer pageSize) {
        log.trace("Executing findPlanByPage [{}]", page, pageSize);
        Integer index = page * pageSize;
        return placeRespository.findAllByPage(index, pageSize);
    }

    @Override
    public List<EmergencyPlace> findPlaceByCategoryAndPage(String category, Integer page, Integer pageSize) {
        log.trace("Executing findPlaceByCategoryAndPage[{}]", category, page, pageSize);
        Integer index = page * pageSize;
        return placeRespository.findPlaceByCategoryAndPage(category, page, pageSize);
    }

    @Override
    public Integer findPlacePageNum(Integer size) {
        log.trace("Executing findPlacePageNum [{}]", size);
        Integer num = (placeRespository.AllPlaceCount() + size - 1) / size;
        return num;
    }

    @Override
    public EmergencyPlace findPlaceById(Integer place_id) {
        log.trace("Executing findPlaceById [{}]", place_id);
        return placeRespository.findEmergencyPlaceById(place_id);
    }

   /* @Override
    public List<EmergencyPlace> findPlaceByName(String name) {
        log.trace("Executing findPlaceByName [{}]", name);
        return placeRespository.findEmergencyPlaceByName(name);
    }

    @Override
    public List<EmergencyPlace> findPlaceByAffiliation(String affiliation) {
        log.trace("Executing findPlaceByAffiliation [{}]", affiliation);
        return placeRespository.findEmergencyPlaceByAffiliation(affiliation);
    }

    @Override
    public List<EmergencyPlace> findPlaceByLocation(String location) {
        log.trace("Executing findPlaceByLocation [{}]", location);
        return placeRespository.findEmergencyPlaceByLocation(location);
    }*/

    @Override
    public Integer getPlaceCount() {
        log.trace("Executing getPlaceCount [{}]");
        Integer count = placeRespository.AllPlaceCount();
        return count;
    }

    @Override
    public Integer PlaceCountOfCategory(String category) {
        log.trace("Executing PlaceCountOfCategory[{}]");
        Integer count = placeRespository.PlaceCountOfCategory(category);
        return count;
    }

    @Override
    public void save(EmergencyPlace emergencyPlace) {
        log.trace("Executing save [{}]");
        placeRespository.save(emergencyPlace);
    }

    @Override
    public void update(EmergencyPlace emergencyPlace) {
        log.trace("Executing update [{}]");
        placeRespository.update(emergencyPlace);
    }

    @Override
    public void deleteById(Integer place_id) {
        log.trace("Executing deleteById [{}]");
        placeRespository.deleteById(place_id);
    }

    @Override
    public List<EmergencyPlace> findAllPlace() {
        log.trace("Executing findAllPlace [{}]");
        return placeRespository.findAllPlace();
    }

}
