package cn.edu.bupt.demo.controller;

import cn.edu.bupt.demo.annotation.Auth;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zy
 * @date 2018/12/6 下午12:00
 */

@RestController
@RequestMapping("/api/v1/info")
@CrossOrigin
@Api(description= "文件上传与下载")
public class DocumentsController {

    String storePath= "/home/zy/info/EmergencyPlanFile";//存放上传的文件路径
//    String storePath = "/Users/zy/Desktop/file";


    //获取所有文件接口
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor"})
    @RequestMapping(value = "/showFile/{name}/{type}", method = RequestMethod.GET)
    public String getAllFile(@PathVariable("name") String name,
                             @PathVariable("type") Integer type) throws IOException {
        JsonObject jsonObject = new JsonObject();
        List<String> filenames = new LinkedList<>();
        File filePath = new File(storePath+"/"+name+"/"+type+"/");
        if(filePath.exists()){
            File[] files = filePath.listFiles();
            if(files!=null){
                for(File file:files)
                {
                    String encodeName = new String(file.getName().getBytes(), "utf-8");
                    filenames.add(encodeName);
                }
            }else{
                System.out.println("文件夹为空");
            }
            jsonObject.addProperty("filenames",filenames.toString());
        }
        return jsonObject.toString();
    }

    //上传文件接口
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("name") String name,
                             @RequestParam("type") Integer type) throws Exception{
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }

            // 获取文件名
            String fileName = file.getOriginalFilename();
            fileName = URLDecoder.decode(fileName,"UTF-8");
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String newPath = storePath+"/"+name+"/"+type;
            File filePath = new File(newPath, fileName);
            if (!filePath.getParentFile().exists()) {

                filePath.getParentFile().mkdirs();//如果目录不存在，创建目录

            }

            String path = newPath+File.separator+fileName;
            File dest = new File(path);
            file.transferTo(dest);// 文件写入
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }


    //下载文件接口
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher","BranchMonitor","Repairman"})
    @RequestMapping(value = "/download/{name}/{type}/{fileName}/{fileType}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable("name") String name,
                             @PathVariable("type") Integer type,
                             @PathVariable("fileName") String filename,
                             @PathVariable("fileType") String fileType,
                             HttpServletResponse response,
                             HttpServletRequest request) throws IOException {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        filename = URLDecoder.decode(filename,"UTF-8");
        FileInputStream fis = null;
        try {
            File file = new File(storePath+"/"+name+"/"+type+"/"+filename+"."+fileType);
            fis = new FileInputStream(file);
            response.setHeader("charset", "utf-8");
            String encodeName = URLEncoder.encode(file.getName(), StandardCharsets.UTF_8.toString());
            response.setHeader("Content-Disposition", "attachment; filename="+encodeName);
            IOUtils.copy(fis,response.getOutputStream());
            response.flushBuffer();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //删除文件接口
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor","BranchDispatcher"})
    @RequestMapping(value = "/delete/{name}/{type}/{fileName}/{fileType}", method = RequestMethod.DELETE)
    public void deleteFile(@PathVariable("name") String name,
                           @PathVariable("type") Integer type,
                           @PathVariable("fileName") String fileName,
                           @PathVariable("fileType") String fileType) throws UnsupportedEncodingException {

        System.out.println("name1"+fileName);
//        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
//        fileName=new String(fileName.getBytes("iso8859-1"),"UTF-8");
        File file = new File(storePath+"/"+name+"/"+type+"/"+fileName+"."+fileType);
        System.out.println(file.getName()+"|"+file.exists());
        if(file.exists()){
            System.out.println(file.delete());
        }

    }

}
