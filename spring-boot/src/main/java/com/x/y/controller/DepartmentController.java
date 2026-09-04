package com.x.y.controller;


import com.x.y.bean.Department;
import com.x.y.bean.Employee;
import com.x.y.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;

@Controller
public class DepartmentController {


    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping("/emp")
    public String toAddPage(Model model) {
        // 拿到所有的部门数据
        Collection<Department> department = departmentDao.getDepartment();
        model.addAttribute("departments", department);
        return "emps/add";
    }


    @GetMapping("/emp")
    public String addEmp(Employee employee) {
        return "";
    }


}
