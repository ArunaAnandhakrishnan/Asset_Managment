package com.verinite.assetmangementtool.service;


import com.verinite.assetmangementtool.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService  {
    EmployeeEntity saveEmployee(EmployeeEntity employee);

    List<EmployeeEntity> allEmployees();

    Object getById(String empId);

    Object deleteEmpByID(String empId);

 
    
    List<EmployeeEntity> getActiveAccounts(String str);
    
    List<EmployeeEntity> getAllByLocation(String str);

	Object updateEmp( EmployeeEntity employee);

    
}
