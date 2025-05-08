package com.verinite.assetmangementtool.service;

import com.verinite.assetmangementtool.dto.AssetCounterDto;
import com.verinite.assetmangementtool.entity.AssetsEntity;

import java.util.List;

public interface AssetService {
	
	public Object saveAsset(AssetsEntity asset);
	public List<AssetsEntity> getAllAssets();
	public Object getAssetBySerialNumber(String id);
	public Object updateAsset(AssetsEntity asset);
	public Object deleteAsset(int id);
	public List<AssetsEntity> getThroughStatus(String str);
	public int getCountOfAssigned();
	public int getCountOfUnassigned();
	public int getLaptopCountByLocation(String id);
	public int totalLaptops();
	public int getCountOfUnassignedByLocation(String id);
	public List<AssetCounterDto> getUnassignedAndTotalLaptops();
	public List<AssetsEntity> getLaptopsUnderWarenty();
	public List<AssetsEntity> getLaptopsOverWarenty();
	public Object saveHistory(String serialNo,String empId);
	List<AssetsEntity> getUnAssigned();


}
