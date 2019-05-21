package cn.edu.bupt.demo.dao.ProjectCinformation;

import cn.edu.bupt.demo.entity.ProjectCinformation;

import java.util.List;

public interface InformationService {
    List<ProjectCinformation> findAllByPage(Integer page, Integer pageSize);

    Integer findProjectPageNum(Integer size);

    ProjectCinformation findProjectById(Integer project_id);

    List<ProjectCinformation> findProjectByDepartment(String department);

    List<ProjectCinformation> findProjectByLocation(String location);

    Integer getProjectCount();

    void save(ProjectCinformation projectCinformation);

    void update(ProjectCinformation projectCinformation);

    void deleteById(Integer project_id);

    List<ProjectCinformation> findAllProject();
}
