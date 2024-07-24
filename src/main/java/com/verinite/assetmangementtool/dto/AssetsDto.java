package com.verinite.assetmangementtool.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class AssetsDto {


    private int assetId;

    private String assetName;

    private String serialNo;

    private String userName;

    private String status;

    private String type;

    private Date purchaseDate;

    private Date warrantyDate;

    private String empId;



}