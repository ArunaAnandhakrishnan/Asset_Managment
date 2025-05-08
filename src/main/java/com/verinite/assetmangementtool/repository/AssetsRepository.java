package com.verinite.assetmangementtool.repository;

import com.verinite.assetmangementtool.entity.AssetsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AssetsRepository extends JpaRepository<AssetsEntity, Number> {


	List<AssetsEntity> findByStatus(String str);
	
	@Query(value = "select laptop_count from tbl_Count where id=?1",nativeQuery = true)	
	int getTotal(int id);

	AssetsEntity findBySerialNumber(String id);

	

	
	
	

}
