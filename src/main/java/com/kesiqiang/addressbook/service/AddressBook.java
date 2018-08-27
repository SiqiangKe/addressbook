package com.kesiqiang.addressbook.service;

import com.kesiqiang.addressbook.dataobject.PersonalInformation;
import com.kesiqiang.addressbook.dataobject.Result;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @创建人 SiqiangKe
 * @创建时间 2018/8/26
 * @描述
 */
public interface AddressBook {

    // 查询所有人的信息
    List<PersonalInformation> findAll();

    // 添加一个人的信息
    Result<PersonalInformation> addOne(PersonalInformation person);

    // 通过姓名查找信息(模糊搜索)
    List<PersonalInformation> findPersonByNameLike(String name);

    // 通过Excel文件导入到数据库
    Result exceltodatabase();

    // 更新人员信息
    Result<PersonalInformation> update(String name, PersonalInformation person);

    // 导出数据为 excel 文件
    Result downloadExcel(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
