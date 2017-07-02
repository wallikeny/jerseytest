package com.xiaogang.study.jerseytest.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by xiaogang on 2017/7/1.
 */
public class StudentCache {
    private static HashMap<String, Student> cache;

    static {
        cache = new HashMap<String, Student>();
    }

    static String add(String name, Gender gender, String addr) {
        String id =  UUID.randomUUID().toString();
        Student student = new Student(id, name, gender, addr);
        cache.put(id, student);
        return id;
    }

    static String add(Student student) {
        String id = student.getId();
        cache.put(id, student);
        return id;
    }

    public static Student update(String id, String name, Gender gender, String addr) {
        Student student = null;
        if (!cache.containsKey(id)) {
            student = new Student(id, name, gender, addr);
            cache.put(id, student);
            return student;
        }

        student = cache.get(id);
        if (name != null && name.trim().length() != 0) {
            student.setName(name);
        }
        if (addr != null && addr.trim().length() != 0) {
            student.setAddr(addr);
        }
        if (gender != null) {
            student.setGender(gender);
        }
        cache.remove(id);
        cache.put(id, student);
        return student;
    }

    public static List<Student> getAll() {
        List<Student> list = new ArrayList<Student>();
        for (Student student: cache.values()) {
            list.add(student);
        }
        return list;
    }

    public static Student get(String id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
        return null;
    }

    public static boolean delete(String id) {
        if (cache.containsKey(id)) {
            cache.remove(id);
        }
        return true;
    }

    public static boolean clear() {
        cache.clear();
        return true;
    }
}
