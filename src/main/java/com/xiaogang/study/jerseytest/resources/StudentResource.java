package com.xiaogang.study.jerseytest.resources;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * Created by xiaogang on 2017/6/28.
 */

@SwaggerDefinition(
        info = @Info(
                description = "restapi demo, for how to design a friendly api",
                version = "beta",
                title = "restapi demo",
                contact = @Contact(
                        name = "wallikeny",
                        email = "wallikeny@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        consumes = {"application/json"},
        produces = {"application/json"},
        schemes = {SwaggerDefinition.Scheme.HTTP}
)

@Api
@Path("v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentResource {
    private static final String DefaultResult = "{\"hello\": \"world\"}";

    @GET
    @Path("students")
    @ApiOperation(value = "List all students",
            notes = "something",
            response = Student.class,
            responseContainer = "List")
    public String actionIndex() {
        List<Student> students = StudentCache.getAll();
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        for (Student s: students) {
            result.add(s.toMapObject());
        }
        return JSON.toJSONString(result);
    }

    @GET
    @Path("student/{id}")
    @ApiOperation(value = "查看学生详情",
        notes = "something",
        response = Student.class)
    public String actionView(
            @ApiParam(value = "id that represent a student", required = true) @PathParam("id") String id) {
        //Response resp = Response.noContent().build();
        Student student = StudentCache.get(id);
        if (student == null) {
            return "{}";
        }
        return JSON.toJSONString(student.toMapObject());
    }

    @POST
    @Path("student")
    @ApiOperation(value = "添加学生信息",
        response = Student.class)
    /*@ApiImplicitParams({
        @ApiImplicitParam(name = "name", value = "student's name", required = true, dataType = "string", paramType = "body"),
        @ApiImplicitParam(name = "gender", value = "student's gender", required = true, dataType = "string", paramType = "body", allowableValues = "FEMALE, MALE"),
        @ApiImplicitParam(name = "addr", value = "student's address", required = true, dataType = "string", paramType = "body")
    })*/
    public String actionCreate(
            @ApiParam(value = "add a student info, please leave item(id) empty", required = true) Student student) {
        if (!student.valid()) {
            return "{}";
        }
        String id = UUID.randomUUID().toString();
        student.setId(id);
        StudentCache.add(student);
        return JSON.toJSONString(student.toMapObject());
    }

    @PUT
    @Path("student/{id}")
    @ApiOperation(value = "修改学生详情",
        notes = "something",
        response = Student.class)
    /*@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "student's id", required = true, dataType = "string", paramType = "path"),
        @ApiImplicitParam(name = "name", value = "student's name", required = true, dataType = "string", paramType = "body"),
        @ApiImplicitParam(name = "gender", value = "student's gender", required = true, dataType = "string", paramType = "body", allowableValues = "FEMALE, MALE"),
        @ApiImplicitParam(name = "addr", value = "student's address", required = true, dataType = "string", paramType = "body")
    })*/
    public String actionUpdate(
            @ApiParam(value = "id that represent a student", required = true) @PathParam("id") String id,
            @ApiParam(value = "update student info", required = true) Student student) {
        Student ret = StudentCache.update(id, student.getName(), student.getGender(), student.getAddr());
        return JSON.toJSONString(ret.toMapObject());
    }

    @DELETE
    @Path("student/{id}")
    @ApiOperation(value = "删除学生",
            notes = "something")
    public String actionDelete(
            @ApiParam(value = "id that represent a student", required = true) @PathParam("id") String id) {
        StudentCache.delete(id);
        return "{}";
    }
}
