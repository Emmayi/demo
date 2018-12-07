package cn.edu.bupt.demo.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author zy
 * @date 2018/12/6 下午12:00
 */

@RestController
@RequestMapping("/api/v1/info")
@CrossOrigin
public class DocumentsController {

//    String storePath= "/home/zy/file";//存放我们上传的文件路径
    String storePath = "/Users/zy/Desktop/file";

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file) throws Exception{
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }

            // 获取文件名
            String fileName = file.getOriginalFilename();
            fileName = URLDecoder.decode(fileName,"UTF-8");
            String suffixName = fileName.substring(fileName.lastIndexOf("."));

            File filePath = new File(storePath, fileName);
            if (!filePath.getParentFile().exists()) {

                filePath.getParentFile().mkdirs();//如果目录不存在，创建目录

            }

            String path = storePath+File.separator+fileName;
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


    @RequestMapping(value = "/download/{filename}/{fileType}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable("filename") String filename,
                             @PathVariable("fileType") String fileType,
                             HttpServletResponse response,
                             HttpServletRequest request) throws IOException {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        filename = URLDecoder.decode(filename,"UTF-8");
        FileInputStream fis = null;
        try {
            File file = new File(storePath+"/"+filename+"."+fileType);
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

    @RequestMapping(value = "/delete/{fileName}/{fileType}", method = RequestMethod.DELETE)
    public void deleteFile(@PathVariable("fileName") String fileName,
                           @PathVariable("fileType") String fileType) throws UnsupportedEncodingException {

        System.out.println("name1"+fileName);
//        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
//        fileName=new String(fileName.getBytes("iso8859-1"),"UTF-8");
        File file = new File(storePath+"/"+fileName+"."+fileType);
        System.out.println(file.getName()+"|"+file.exists());
        if(file.exists()){
            System.out.println(file.delete());
        }

    }

}
