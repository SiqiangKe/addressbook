/**
 * FileName: PersonalInformation
 * Author:   SiqiangKe
 * Date:     2018/8/25 19:10
 * Description: 个人通讯信息
 */
package com.kesiqiang.addressbook.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PersonalInformation{

    @Id
    @GeneratedValue
    private Integer id;

    // 姓名
    @Column(unique = true)
    private String name;

    // 手机
    @Column(unique = true)
    private String phoneNumber;

    // 所在地址
    @Column(unique = true)
    private String city;

    // 数据表对象必须有一个空的构造方法
    public PersonalInformation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "PersonalInformation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
