package com.verinite.assetmangementtool.service;

import com.verinite.assetmangementtool.dto.AssetNameDTO;
import com.verinite.assetmangementtool.repository.AssetNamesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AssetNameServiceImpl implements AssetNamesInterface{
	@Autowired
	AssetNamesRepo assetNamesRepo;

	@Override
	public AssetNameDTO save(AssetNameDTO assetNamesDto) {
		assetNamesDto.setAssetName(assetNamesDto.getAssetName().toLowerCase());
		if(assetNamesRepo.findByAssetName(assetNamesDto.getAssetName())!=null){
			return assetNamesDto;
		}else {

			return assetNamesRepo.save(assetNamesDto);
		}

	}

	@Override
	public List<AssetNameDTO> getAll() {
		return assetNamesRepo.findAll();
	}
	

}
