package com.verinite.assetmangementtool.controller;

import com.verinite.assetmangementtool.entity.AssetsEntity;
import com.verinite.assetmangementtool.service.mailservice.AckMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/assetManager/v1/service")
public class MailController {
    @Autowired
    private AckMail ackMail;

    @PostMapping("/send/ack/mail/{empId}")
    public ResponseEntity<?> sendMailToEmp(@PathVariable String empId, @RequestBody List<AssetsEntity> assetsEntities) throws MessagingException, UnsupportedEncodingException {
        return ackMail.sendAckMail(empId,assetsEntities);
    }
}
