package com.lsy.service_edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author : Lo Shu-ngan
 * @Classname excelSubjectData
 * @Description excel实体类
 * @Date 2020/08/04 22:59
 */
@Data
public class ExcelSubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
