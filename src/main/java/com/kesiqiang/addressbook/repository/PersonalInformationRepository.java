package com.kesiqiang.addressbook.repository;

import com.kesiqiang.addressbook.dataobject.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @创建人 SiqiangKe
 * @创建时间 2018/8/25
 * @描述
 */
public interface PersonalInformationRepository extends JpaRepository<PersonalInformation, Integer> {

    // 通过姓名来查询(模糊查询)
    List<PersonalInformation> findByNameLike(String name);

    // 通过姓名查询（精确查询）
    PersonalInformation findByName(String name);


}
