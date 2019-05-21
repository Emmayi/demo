package cn.edu.bupt.demo.dao.ProjectCinformation;

import cn.edu.bupt.demo.entity.ProjectCinformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class InformationServiceImpl implements InformationService{
    @Autowired
    InformationRespository informationRespository;

    @Override
    public List<ProjectCinformation> findAllByPage(Integer page, Integer pageSize) {
        log.trace("Executing findProjectByPage [{}]", page,pageSize);
        Integer index = page * pageSize;
        return informationRespository.findAllByPage(index,pageSize);
    }

    @Override
    public Integer findProjectPageNum(Integer size) {
        log.trace("Executing findProjectPageNum [{}]", size);
        Integer num = (informationRespository.AllProjectCount()+size-1)/size;
        return num;
    }

    @Override
    public ProjectCinformation findProjectById(Integer project_id) {
        log.trace("Executing findProjectById [{}]", project_id);
        return informationRespository.findProjectById(project_id);
    }


    @Override
    public List<ProjectCinformation> findProjectByDepartment(String department) {
        log.trace("Executing findProjectByDepartment [{}]", department);
        return informationRespository.findProjectByDepartment(department);
    }

    @Override
    public List<ProjectCinformation> findProjectByLocation(String location) {
        log.trace("Executing findProjectByLocation [{}]", location);
        return informationRespository.findProjectByLocation(location);
    }

    @Override
    public Integer getProjectCount() {
        log.trace("Executing getProjectCount [{}]");
        Integer count = informationRespository.AllProjectCount();
        return count;
    }

    @Override
    public void save(ProjectCinformation projectCinformation) {
        log.trace("Executing save [{}]");
        informationRespository.save(projectCinformation);
    }

    @Override
    public void update(ProjectCinformation projectCinformation) {
        log.trace("Executing update [{}]");
        informationRespository.update(projectCinformation);
    }

    @Override
    public void deleteById(Integer project_id) {
        log.trace("Executing deleteById [{}]");
        informationRespository.deleteById(project_id);
    }

    @Override
    public List<ProjectCinformation> findAllProject() {
        log.trace("Executing findAllProject [{}]");
        return informationRespository.findAllProject();
    }

}
