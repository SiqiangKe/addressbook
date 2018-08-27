/**
 * FileName: AddressBookService
 * Author:   SiqiangKe
 * Date:     2018/8/26 14:47
 * Description:
 */
package com.kesiqiang.addressbook.service;

import com.kesiqiang.addressbook.dataobject.PersonalInformation;
import com.kesiqiang.addressbook.dataobject.Result;
import com.kesiqiang.addressbook.repository.PersonalInformationRepository;
import com.kesiqiang.addressbook.utils.DownloadExcel;
import com.kesiqiang.addressbook.utils.ExcelAndDatabase;
import com.kesiqiang.addressbook.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;


@Service
public class AddressBookService implements AddressBook {

    @Autowired
    private PersonalInformationRepository personalInformationRepository;

    @Override
    public List<PersonalInformation> findAll() {
        return personalInformationRepository.findAll();
    }

    @Override
    public Result<PersonalInformation> addOne(PersonalInformation person) {

        person.setName(person.getName());
        person.setPhoneNumber(person.getPhoneNumber());
        person.setCity(person.getCity());

        return ResultUtil.success(personalInformationRepository.save(person));
    }

    @Override
    public List<PersonalInformation> findPersonByNameLike(String name) {
        return personalInformationRepository.findByNameLike("%" + name + "%");
    }

    @Override
    public Result<PersonalInformation> update(String name, PersonalInformation person) {
        PersonalInformation p = personalInformationRepository.findByName(name);

        p.setName(person.getName());
        p.setCity(person.getCity());
        p.setPhoneNumber(person.getPhoneNumber());

        return ResultUtil.success(personalInformationRepository.saveAndFlush(p));
    }

    @Override
    public Result exceltodatabase(){
        List<List<String>> lists = ExcelAndDatabase.readExcel("C:\\Users\\WireLess_Lab\\Desktop\\2016级通信工程系通讯录.xlsx");
        for (int j = 0; j < lists.size(); j++) {
            List list = lists.get(j);
            PersonalInformation p = new PersonalInformation();
            p.setName(list.get(0).toString());
            p.setPhoneNumber(list.get(1).toString());
            p.setCity(list.get(2).toString());
            personalInformationRepository.save(p);
        }
        return ResultUtil.success();
    }

    @Override
    public Result downloadExcel(HttpServletRequest request,
                              HttpServletResponse response) throws IOException{

        String path = DownloadExcel.createExcel(personalInformationRepository.findAll());

        String fileName = "通讯录.xls";
        if (fileName != null) {
            File file = new File(path);
            if (file.exists()) {
                response.setContentType("multipart/form-data");// 自动判断下载类型
                //采用UTF-8编码
                response.setHeader("Content-disposition","attachment; filename="+ URLEncoder.encode(fileName, "UTF-8"));
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("download success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return ResultUtil.success();
    }

}
