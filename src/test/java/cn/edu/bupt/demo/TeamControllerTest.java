package cn.edu.bupt.demo;

import cn.edu.bupt.demo.controller.TeamController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author zy
 * @date 2022/1/12 下午8:57
 */
public class TeamControllerTest {

    private Integer pages = 0;
    private TeamController teamController = new TeamController();

    @Before
    public void before(){

    }

    @Test
    public void TestGetTeamPages(){
        try {
            assertEquals(pages,teamController.getTeamPages(10));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void after(){

    }

}
