package com.hyperskill.university.controllers;

import com.hyperskill.university.models.Department;
import com.hyperskill.university.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/department")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class DepartmentController extends OncePerRequestFilter {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<Department> showDepartments() {
        return departmentService.getDepartments();
    }

    @GetMapping(path = {"{departmentId}"})
    public Department showDepartment(@PathVariable("departmentId") Integer departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping
    public Department addDepartment(@RequestBody Department department) {
        return departmentService.addNewDepartment(department);
    }

    @DeleteMapping(path = {"{departmentId}"})
    public void deleteDepartment(@PathVariable Integer departmentId) {
        departmentService.deleteDepartment(departmentId);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
        response.addHeader("Access-Control-Max-Age", "3600");
        filterChain.doFilter(request
                , response);
    }
}
