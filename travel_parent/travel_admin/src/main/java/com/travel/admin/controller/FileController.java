package com.travel.admin.controller;



import com.travel.common.util.ResResult;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description: 文件上传
 */
@Controller
public class FileController {

    @Value("${file.upload.path}")
    private String uploadFilePath ;
    @Value("${file.upload.spotsFmPath}")
    private String  uploadSpotsImgFilePath;

    @Value("${file.upload.headImgPath}")
    private String  headImgPath;



    @PostMapping(value="/user/file/uploadFile")
    @ResponseBody
    public ResResult<Map<String, Object>> upload(HttpServletRequest req, @RequestParam("file") MultipartFile file){
        try {
            Map<String, Object> resultMap = new HashMap();
            if(file.isEmpty()){
                resultMap.put("message","文件为空");
                return new ResResult<Map<String, Object>>(ResResult.CodeStatus.FAIL, "上传失败", resultMap);
            }
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String uuidString = UUID.randomUUID().toString();
            String newFileName= uuidString + suffixName;
            String uploadPath = headImgPath;
            File path = new File(uploadPath);
            if (!path.exists()) path.mkdirs();

            File savefile = new File(path,newFileName);
            if (!savefile.getParentFile().exists()) savefile.getParentFile().mkdirs();
            file.transferTo(savefile);

            resultMap.put("fileUrl", newFileName);
            return new ResResult<Map<String, Object>>(ResResult.CodeStatus.OK, "上传成功", resultMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/user/showimage")
    public String showImage(@RequestBody  Map mp,HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String image_name = (String)mp.get("image_name");
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        //response.setContentType("image/jpeg");
        response.setContentType("application/octet-stream;charset=UTF-8");
        //设置Content-Disposition头 以附件形式解析
        response.addHeader("Content-Disposition", "attachment;filename=" + image_name);

        // 获得的系统的根目录
        File fileParent = new File(File.separator);
        // 获得/usr/CBeann目录
        System.out.println("读取头像:"+image_name);
        File file = null ;
        String os = System.getProperty("os.name");
        ServletOutputStream out = response.getOutputStream();
        if(image_name!=null){
            try {
                String uploadPath = headImgPath;
                if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统
                    file = new File(uploadPath +"\\"+ image_name);
                } else {  //linux 和mac
                    file = new File(fileParent, uploadPath.substring(1) +"/"+ image_name);
                }
                if(file.exists()) {
                    IOUtils.copy(new FileInputStream(file), out);
                }
                out.flush();
            } finally {
                out.close();
            }
        }
        return null;
    }


    @PostMapping(value="/spots/file/uploadFile")
    @ResponseBody
    public ResResult<Map<String, Object>> uploadSpotsFmUrl(HttpServletRequest req, @RequestParam("file") MultipartFile file){
        try {
            Map<String, Object> resultMap = new HashMap();
            if(file.isEmpty()){
                resultMap.put("message","文件为空");
                return new ResResult<Map<String, Object>>(ResResult.CodeStatus.FAIL, "上传失败", resultMap);
            }
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String uuidString = UUID.randomUUID().toString();
            String newFileName= uuidString + suffixName;
            String uploadPath = uploadSpotsImgFilePath;
            File path = new File(uploadPath);
            if (!path.exists()) path.mkdirs();

            File savefile = new File(path,newFileName);
            if (!savefile.getParentFile().exists()) savefile.getParentFile().mkdirs();
            file.transferTo(savefile);

            resultMap.put("fileUrl", newFileName);
            return new ResResult<Map<String, Object>>(ResResult.CodeStatus.OK, "上传成功", resultMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @PostMapping(value="/notes/file/uploadFile")
    @ResponseBody
    public ResResult<Map<String, Object>> uploadNotesFmUrl(HttpServletRequest req, @RequestParam("file") MultipartFile file){
        try {
            Map<String, Object> resultMap = new HashMap();
            if(file.isEmpty()){
                resultMap.put("message","文件为空");
                return new ResResult<Map<String, Object>>(ResResult.CodeStatus.FAIL, "上传失败", resultMap);
            }
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String uuidString = UUID.randomUUID().toString();
            String newFileName= uuidString + suffixName;
            String uploadPath = uploadFilePath+"/notes";
            File path = new File(uploadPath);
            if (!path.exists()) path.mkdirs();

            File savefile = new File(path,newFileName);
            if (!savefile.getParentFile().exists()) savefile.getParentFile().mkdirs();
            file.transferTo(savefile);

            resultMap.put("fileUrl", newFileName);
            return new ResResult<Map<String, Object>>(ResResult.CodeStatus.OK, "上传成功", resultMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




}
