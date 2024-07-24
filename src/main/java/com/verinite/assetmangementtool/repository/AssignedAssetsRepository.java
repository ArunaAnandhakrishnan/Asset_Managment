package com.verinite.assetmangementtool.repository;

import com.verinite.assetmangementtool.entity.AssetsEntity;
import com.verinite.assetmangementtool.entity.AssignedAssetsEntity;
import org.springframework.beans.PropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AssignedAssetsRepository extends JpaRepository<AssignedAssetsEntity, Integer>{

	@Query(value="select from tbl_assined_assets where assigned_assets_id =?",nativeQuery = true)
	AssignedAssetsEntity getAssignedAssetsById(int assignedId);

	@Query(value="select from tbl_assined_assets where assets_id =?",nativeQuery = true)
	AssignedAssetsEntity getAssignedAssetsByAssetsId(int assetId);

	AssignedAssetsEntity findBySerialNumber(String serialNo);

    AssignedAssetsEntity findBySerialNumberAndEmpId(String serialNo, String empId);

    List<AssignedAssetsEntity> findByEmpId(String empId);

	List<AssignedAssetsEntity> findByEmpIdNotNull();
}
