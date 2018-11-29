package cn.edu.bupt.demo.controller; 

import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.*;

/** 
* StaffController Tester. 
* 
* @author zy 
* @since <pre>十一月 27, 2018</pre> 
* @version 1.0 
*/ 
public class StaffControllerTest { 

@Before
public void before() throws Exception {
    System.out.println("this is test before!");
} 

@After
public void after() throws Exception {
    System.out.println("this is test after");
}

/** 
* 
* Method: getStaffById(@RequestParam Integer staffId) 
* 
*/ 
@Test
public void testGetStaffById() throws Exception { 
//TODO: Test goes here...
    System.out.println("this is testGetStaffById");
} 

/** 
* 
* Method: getStaffByName(@RequestParam String staffName) 
* 
*/ 
@Test
public void testGetStaffByName() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: createStaff(@RequestBody String staffInfo) 
* 
*/ 
@Test
public void testCreateStaff() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: updateStaff(@RequestBody String staffInfo) 
* 
*/ 
@Test
public void testUpdateStaff() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: staffCountById() 
* 
*/ 
@Test
public void testStaffCountById() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findStaffName(@RequestParam Integer staffId) 
* 
*/ 
@Test
public void testFindStaffName() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: deleteStaffById(@RequestParam Integer staffId) 
* 
*/ 
@Test
public void testDeleteStaffByIdStaffId() throws Exception { 
//TODO: Test goes here...
} 

/** 
* 
* Method: deleteStaffById(@RequestParam String staffName) 
* 
*/ 
@Test
public void testDeleteStaffByIdStaffName() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findAllStaff() 
* 
*/ 
@Test
public void testFindAllStaff() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: Json2Staff(JsonObject staffString) 
* 
*/ 
@Test
public void testJson2Staff() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = StaffController.getClass().getMethod("Json2Staff", JsonObject.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
