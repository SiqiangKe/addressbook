/**
 * FileName: InformationController
 * Author:   SiqiangKe
 * Date:     2018/8/25 19:32
 * Description:
 */
package com.kesiqiang.addressbook.controller;

import com.kesiqiang.addressbook.dataobject.PersonalInformation;
import com.kesiqiang.addressbook.dataobject.Result;
import com.kesiqiang.addressbook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/")
public class InformationController {

    @Autowired
    private AddressBookService addressBookService;

    // 查询所有人的信息
    @GetMapping(value = "findall")
    public List<PersonalInformation> findAll(){
        return addressBookService.findAll();
    }


    // 添加一个人的信息
    @PostMapping(value = "addone")
    public Result<PersonalInformation> addOne(PersonalInformation person){
        return addressBookService.addOne(person);
    }

    // 通过姓名查找人
    @GetMapping(value = "find/{name}")
    public List<PersonalInformation> findPersonByNameLike(@PathVariable("name") String name){
        return addressBookService.findPersonByNameLike(name);
    }

    // 更新信息
    @PutMapping(value = "update/{name}")
    public Result<PersonalInformation> update(@PathVariable("name") String name,
            PersonalInformation person){
        return addressBookService.update(name, person);
    }

    @GetMapping(value = "import")
    public void exceltodatabase(){
        addressBookService.exceltodatabase();
    }

    @GetMapping(value = "download")
    public void downloadExcel(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        addressBookService.downloadExcel(request, response);
    }


}
