package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.model.response.Response;
import com.example.demo.service.UserService;
import com.example.demo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/upload")
public class UploadController {
    @Value("${upload.file.path}")
    private String file_path; // 文件上传的根目录
    @Autowired
    private UserService userService;

    /**
     * 上传图片
     * @param imageFiles
     * @param uid
     * */
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public Response upload(@RequestParam("imageFiles") MultipartFile[] imageFiles, @RequestParam("uid") Integer uid) throws Exception{
        Response response = new Response();
        // 图片存储在file_path路径的image目录下
        String pathTo = "\\image";
        try {
            Response result = saveImg(imageFiles, uid, pathTo);
            response.setStatus(result.getStatus());
            response.setMassage(result.getMassage());
        } catch (Exception e) {
            response.setStatus(0);
            response.setMassage("error:" + e);
        }

        return response;
    }

    /**
     * 存储图片
     * @param imageFiles
     * @param uid
     * @param pathTo
     * */
    public Response saveImg(MultipartFile[] imageFiles, Integer uid, String pathTo) throws Exception {
        Response response = new Response();

        if (imageFiles != null && imageFiles.length > 0) {
            List<String> path = new ArrayList<>();

            int i = 0;
            for (MultipartFile imageFile: imageFiles) {
                String fileName = imageFile.getOriginalFilename();
                // 判断是否有文件且是否为图片
                if (!StringUtil.isNull(fileName) && isImageFile(fileName)) {
                    // 创建输出文件对象
                    String saveFileName = UUID.randomUUID().toString() + getFileType(fileName);
                    File outFile = new File(file_path + '\\' + saveFileName);
                    imageFile.transferTo(outFile);
                    path.add(file_path + pathTo + '\\' + saveFileName);
                    i++;
                }
            }

            String str= String.join(",", path.toArray(new String[path.size()]));
            if ("".equals(str)) { // 当下载文件目标不是图片时
                System.out.println("目标不是图片类型！");
                response.setStatus(0);
                response.setMassage("文件上传失败，文件不是图片类型");
                return response;
            }

            User user = userService.getUserByUid(uid);
            user.setAvator(str);
            try {
                userService.updateUser(user);
            } catch (Exception e) {
                response.setStatus(0);
                response.setMassage("数据库保存失败: " + e);
            }
        } else {
            response.setStatus(0);
            response.setMassage("文件上传失败");
        }

        return response;
    }

    /**
     * 判断文件是否为图片
     * @param fileName
     * @return
     * */
    private boolean isImageFile(String fileName) {
        String[] img_type = new String[]{".jpg",".jpeg",".png",".gif",".bmp"};
        if (StringUtil.isNull(fileName)) {
            return false;
        }
        fileName = fileName.toLowerCase();
        for (String type: img_type) {
            if (fileName.endsWith(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件后缀名
     * @param fileName
     * @return
     * */
    private String getFileType(String fileName) {
        if (fileName != null && fileName.indexOf(".") >= 0) {
            return fileName.substring(fileName.lastIndexOf("."), fileName.length());
        }
        return "";
    }
}
