package com.verinite.assetmangementtool.controller;

import com.verinite.assetmangementtool.dto.AdminLoginDto;
import com.verinite.assetmangementtool.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JwtAuthenticationController {
    @Autowired
    AdminServiceImpl service;

    @RequestMapping(value="/login",method= RequestMethod.POST)
    public ResponseEntity<?> adminLogin(@RequestBody AdminLoginDto login) throws Exception {
        //LOGGER.info("inside method!!!: login method", login.getEmpId());
        return service.checkLogin(login);
    }
}
