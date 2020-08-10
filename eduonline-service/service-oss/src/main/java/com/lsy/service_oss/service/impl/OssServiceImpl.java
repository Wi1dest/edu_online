package com.lsy.service_oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.lsy.service_oss.service.OssService;
import com.lsy.service_oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author : Lo Shu-ngan
 * @Classname OssServiceImpl
 * @Description Oss接口实现类
 * @Date 2020/08/04 18:22
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。(ConstantPropertiesUtil类中获取)
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 获取上传文件输入流。
            InputStream inputStream = file.getInputStream();

            // 获取文件名称
            String filename = file.getOriginalFilename();

            //为了避免由于文件夹名称一样导致被覆盖 , 生成随机值 (.replace("-","") 把uuid中的-替换掉)
            String uuid = UUID.randomUUID().toString().replace("-","");
            filename = uuid + filename;

            // 把文件按日期分类 2020/11/11/xxx.jpg
            String datePath = new DateTime().toString("yyyy/MM/dd");
            filename = datePath + "/" + filename;

            // 参1 bucket名字
            // 参2 上传到oss文件路径和名称 (可以利用这个来建文件夹)
            // 参3 文件输入流
            ossClient.putObject(bucketName, filename, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            // 上传之后文件路径返回
            StringBuilder avatarUrl = new StringBuilder();
            avatarUrl.append("https://").append(bucketName).append(".").append(endpoint).append("/").append(filename);
            String url = String.valueOf(avatarUrl);
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String uploadFileBanner(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。(ConstantPropertiesUtil类中获取)
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 获取上传文件输入流。
            InputStream inputStream = file.getInputStream();

            // 获取文件名称
            String filename = file.getOriginalFilename();

            //为了避免由于文件夹名称一样导致被覆盖 , 生成随机值 (.replace("-","") 把uuid中的-替换掉)
            String uuid = UUID.randomUUID().toString().replace("-","");
            filename = uuid + filename;

            // 把文件按日期分类 2020/11/11/xxx.jpg
            String datePath = new DateTime().toString("yyyy/MM/dd");
            filename = datePath + "/banner/" + filename;

            // 参1 bucket名字
            // 参2 上传到oss文件路径和名称 (可以利用这个来建文件夹)
            // 参3 文件输入流
            ossClient.putObject(bucketName, filename, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            // 上传之后文件路径返回
            StringBuilder avatarUrl = new StringBuilder();
            avatarUrl.append("https://").append(bucketName).append(".").append(endpoint).append("/").append(filename);
            String url = String.valueOf(avatarUrl);
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
