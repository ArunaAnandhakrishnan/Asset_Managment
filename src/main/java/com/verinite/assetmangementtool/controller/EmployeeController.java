package com.verinite.assetmangementtool.controller;


import com.verinite.assetmangementtool.entity.EmployeeEntity;
import com.verinite.assetmangementtool.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/assetManager/v1/")
public class EmployeeController {
    @Autowired
    EmployeeServiceImpl employeeService;
    @PostMapping("employee/saveemployee")
    public EmployeeEntity saveEmployee(@RequestBody EmployeeEntity employee) {
       // logger.info("debug");

        return employeeService.saveEmployee(employee);
    }
    @PostMapping("employee/save/bulk")
    public String saveEmployeeBulk(@RequestBody List<EmployeeEntity> employeeList) {
    employeeList.stream().forEach(emp->employeeService.saveEmployee(emp));
    return "List Added Successfully";
    }
    @GetMapping("employee/employeelist")
    public List<EmployeeEntity> allEmployee() {

        return employeeService.allEmployees();
    }
    @GetMapping("/getEmployee/{empId}")
    public Object getById(@PathVariable String empId) {

        return employeeService.getById(empId);
    }
    @GetMapping("/deleteEmp/{empId}")
    public Object deleteEmpById(@PathVariable String empId) {

        return employeeService.deleteEmpByID(empId);
    }
    @PostMapping("/updateEmp")
    public Object updateEmp( @RequestBody EmployeeEntity employee) {
        //logger.info("debug");
        return employeeService.updateEmp( employee);

    }
    
    @GetMapping("employee/status/{active1}")
    public List<EmployeeEntity> getActiveAccounts(@PathVariable String active1){

    	return employeeService.getActiveAccounts(active1);
    }
    
    @GetMapping("/location/{name}")
    public List<EmployeeEntity> getByLocations(@PathVariable String name){
    	return employeeService.getAllByLocation(name); 
    }
    @GetMapping("/employee/get/for/admin/{empId}")
    public ResponseEntity<?> getUserForAdmin(@PathVariable String empId){
        return employeeService.getByIdForAdmin(empId);
    }
}
