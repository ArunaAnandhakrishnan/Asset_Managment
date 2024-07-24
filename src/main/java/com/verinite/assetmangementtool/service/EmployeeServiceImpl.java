package com.verinite.assetmangementtool.service;


import com.verinite.assetmangementtool.entity.EmployeeEntity;
import com.verinite.assetmangementtool.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepo;

    @Override
    public EmployeeEntity saveEmployee(EmployeeEntity employee) {
        employee.setStatus("active");
        employee.setRole("user");
        if (employeeRepo.findByEmpId(employee.getEmpId()) == null) {
            return employeeRepo.save(employee);
        } else {
            new UnknownError();
        }
        return employee;
    }

    @Override
    public List<EmployeeEntity> allEmployees() {
        List<EmployeeEntity> listEmp = employeeRepo.findAll();
        return listEmp.stream().filter(x -> x.getStatus().equalsIgnoreCase("active")).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> getById(String empId) {

        EmployeeEntity emp = employeeRepo.findByEmpId(empId);
        if (emp != null) {
            return ResponseEntity.ok().body(emp);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
    }


    @Override
    public Object deleteEmpByID(String empId) {
        EmployeeEntity employee1 = (EmployeeEntity) employeeRepo.findByEmpId(empId);
        employee1.setStatus("inactive");
        employeeRepo.save(employee1);
        return new EmployeeEntity();
    }

    @Override
    public Object updateEmp(EmployeeEntity employee) {
        try {
            EmployeeEntity employee1 = (EmployeeEntity) employeeRepo.findByEmpId(employee.getEmpId());
            employee1.setDepartment(employee.getDepartment());
            employee1.setDesignation(employee.getDesignation());
            employee1.setFirstName(employee.getFirstName());
            employee1.setLastName(employee.getLastName());
            employee1.setLocation(employee.getLocation());
            employee1.setStatus(employee.getStatus());
            employee1.setMail(employee.getMail());
            employee1.setMobile(employee.getMobile());
            employee1.setRole(employee.getRole());
            employee1.setEmpId(employee.getEmpId());
            return employeeRepo.save(employee1);
        } catch (Exception e) {
            return "record is not present to update with id of " + employee.getEmpId();
        }
    }

    @Override
    public List<EmployeeEntity> getActiveAccounts(String str) {
        return employeeRepo.findByIgnoreCaseStatus(str);
    }

//	

    @Override
    public List<EmployeeEntity> getAllByLocation(String str) {
        return employeeRepo.findByIgnoreCaseLocation(str);
    }


    public ResponseEntity<?> getByIdForAdmin(String empId) {

        EmployeeEntity emp = employeeRepo.findByEmpId(empId);
        if (emp != null) {
            if (emp.getStatus().equalsIgnoreCase("Active")) {
                if (emp.getRole().equalsIgnoreCase("User")) {
                    return ResponseEntity.ok().body(emp);
                } else {
                    return ResponseEntity.status(HttpStatus.FOUND).body("User is Already Admin");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not Found(Inactive)");

            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee is Not found 😥");
        }

    }
}
