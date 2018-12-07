package cn.edu.bupt.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author zy
 * @date 2018/12/6 下午12:00
 */

@RestController
@RequestMapping("/api/v1/info")
@CrossOrigin
public class DocumentsController {

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
            String filePath = "/home/zy/file";
            String path = filePath +"/"+ fileName;
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

    @RequestMapping(value = "/delete/{fileName}/{fileType}", method = RequestMethod.DELETE)
    public void deleteFile(@PathVariable("fileName") String fileName, @PathVariable("fileType") String fileType) throws UnsupportedEncodingException {
        //fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
        System.out.println("enter");
        File file = new File("/root/doc/"+fileName+"."+fileType);
        System.out.println(file.getName()+"|"+file.exists());
        if(file.exists()){
            System.out.println(file.delete());
        }

    }

}
