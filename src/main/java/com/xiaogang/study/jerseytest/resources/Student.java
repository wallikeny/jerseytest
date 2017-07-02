package com.xiaogang.study.jerseytest.resources;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaogang on 2017/7/1.
 */

@ApiModel(value = "Student model", description = "Student model")
public class Student {
    /* unique id */
    private String id;

    /* student name */
    private String name;

    /* student gender */
    private Gender gender;

    /* student address */
    private String addr;

    public Student() {}

    public Student(String id, String name, Gender gender, String addr) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.addr = addr;
    }

    @ApiModelProperty(value = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty(value = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(value = "gender", allowableValues = "FEMALE, MALE")
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @ApiModelProperty(value = "address")
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public boolean valid() {
        return !(name.trim().length() == 0 || addr.trim().length() == 0);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", addr='" + addr + '\'' +
                '}';
    }

    public Map<String, String> toMapObject() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("id", id);
        result.put("name", name);
        result.put("gender", gender.toString());
        result.put("addr", addr);

        return  result;
    }
}
