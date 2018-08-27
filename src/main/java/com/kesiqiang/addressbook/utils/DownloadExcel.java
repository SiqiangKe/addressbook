/**
 * FileName: DownloadExcel
 * Author:   SiqiangKe
 * Date:     2018/8/26 19:48
 * Description:
 */
package com.kesiqiang.addressbook.utils;

import com.kesiqiang.addressbook.dataobject.PersonalInformation;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

public class DownloadExcel {
    public static String createExcel(List<PersonalInformation> list) {

        //存储路径--获取桌面位置
        FileSystemView view = FileSystemView.getFileSystemView();
        File directory = view.getHomeDirectory();
        System.out.println(directory);

        //存储Excel的路径
        String path = directory+"\\"+"通讯录.xls";
        File file = new File(path);

        // 反射获得类的属性个数 ,也就得到了你要打印的列数
        Class obClass = list.get(0).getClass();
        Field[] fields = obClass.getDeclaredFields();
        int columnNum = fields.length;

        // 得到该对象的属性名字，存到数组中去
        String[] titleNames = new String[columnNum];
        for (int i = 0; i < columnNum; i++) {
            titleNames[i] = fields[i].getName();
            System.out.println(titleNames[i]);
        }


        try {
            // 创建工作簿
            WritableWorkbook workbook = Workbook.createWorkbook(file);
            // 创建sheet
            WritableSheet sheet = workbook.createSheet("sheetOne", 0);
            Label label;

            for(int i = 0; i<list.size(); i++){
                //写入标题--类的属性名
                if(i == 0){
                    for(int j = 0; j<columnNum; j++){
                        label = new Label(j,0,titleNames[j]);
                        sheet.addCell(label);
                    }
                }
                Object people = list.get(i);

                //读取每个实体中的字段上的值
                for(int j = 0; j<columnNum; j++){
                    fields[j].setAccessible(true);
                    String value = String.valueOf(fields[j].get(people));
                    label = new Label(j,i+1,value);
                    sheet.addCell(label);
                }
            }
            //写入
            workbook.write();
            // 关闭
            workbook.close();
            System.out.println("写入成功");
        } catch (Exception e) {
            System.out.println("写入失败");
            e.printStackTrace();
        }
        return path;
    }

}
