package com.verinite.assetmangementtool.service;

import com.verinite.assetmangementtool.dto.AssignableAssetDto;
import com.verinite.assetmangementtool.dto.RecentAssignedEmp;
import com.verinite.assetmangementtool.entity.AssetsEntity;
import com.verinite.assetmangementtool.entity.AssignedAssetsEntity;
import com.verinite.assetmangementtool.entity.CountOfAssets;
import com.verinite.assetmangementtool.entity.EmployeeEntity;
import com.verinite.assetmangementtool.repository.AssetCountRepository;
import com.verinite.assetmangementtool.repository.AssetsRepository;
import com.verinite.assetmangementtool.repository.AssignedAssetsRepository;
import com.verinite.assetmangementtool.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class AssignedAssetsServiceImpl implements AssignedAssetsService{

	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	private AssignedAssetsRepository assignedAssetsRepository;

	@Autowired
	AssetsRepository assetsRepo;

	@Autowired
	AssetCountRepository assetCountRepository;

	@Override
	public AssignedAssetsEntity saveAssignedAssets(AssignedAssetsEntity assignedAssetsEntity) {
		return assignedAssetsRepository.save(assignedAssetsEntity);
	}

	@Override
	public AssignedAssetsEntity getAssignedAssetsById(int assignedId) {
		return assignedAssetsRepository.getAssignedAssetsById(assignedId);
	}

	@Override
	public AssignedAssetsEntity getAssignedAssetsByAssetsId(int assetId) {
		return assignedAssetsRepository.getAssignedAssetsByAssetsId(assetId);
	}

	@Override
	public List<AssignedAssetsEntity> getAllassignedAssets() {
		return assignedAssetsRepository.findAll().stream().filter(x->x.getEmpId()!=null).collect(Collectors.toList());

	}

	@Override
	public AssignedAssetsEntity updateAssignedAssets(int assignedId, AssignedAssetsEntity assignedAssetsEntity) {
		AssignedAssetsEntity assignedAssetsEntitys=new AssignedAssetsEntity();
		try {
			assignedAssetsEntitys=assignedAssetsRepository.getAssignedAssetsById(assignedId);
		}catch (Exception e) {
			System.out.println("Given id not found");
		}

		assignedAssetsEntitys.setAssetId(assignedAssetsEntity.getAssetId());

		return assignedAssetsRepository.save(assignedAssetsEntitys);
	}



	@Override
	public String deleteAssignedAssets(int assignedId) {
		String deleted ="Record deleted successfully";
		AssignedAssetsEntity assignedAssetsEntitys=new AssignedAssetsEntity();
		try {
			assignedAssetsEntitys=assignedAssetsRepository.getAssignedAssetsById(assignedId);
		}catch (Exception e) {
			return "Given id not found";
		}
		assignedAssetsRepository.save(assignedAssetsEntitys);
		return deleted;
	}

	@Override
	public ResponseEntity<?> save(AssignableAssetDto assignableAssetDto) {
		String id=assignableAssetDto.getEmpId();
		EmployeeEntity employeeEntity=(EmployeeEntity) employeeRepository.findByEmpId(id);
		if(employeeEntity!=null) {
			AssetsEntity asset=assetsRepo.findBySerialNumber(assignableAssetDto.getSerialNumber());
			AssignedAssetsEntity assignedAssetsEntity=new AssignedAssetsEntity();
			try {
				List<CountOfAssets> countOfAssets=assetCountRepository.findAll();
				if(!asset.getStatus().equalsIgnoreCase("scrap")) {
					assignedAssetsEntity.setAssetId(asset.getAssetId());
					assignedAssetsEntity.setAssetName(asset.getAssetName());
					assignedAssetsEntity.setEmpId(assignableAssetDto.getEmpId());
					assignedAssetsEntity.setLocation(asset.getLocation());
					assignedAssetsEntity.setModelName(asset.getModelName());
					assignedAssetsEntity.setOperatingSystem(asset.getOperatingSystem());
					assignedAssetsEntity.setPurchaseDate(asset.getPurchaseDate());
					assignedAssetsEntity.setWarrantyDate(asset.getWarrantyDate());
					assignedAssetsEntity.setAssignedBy(assignableAssetDto.getAssignedBy());
					assignedAssetsEntity.setAssignedDate(assignableAssetDto.getAssignedDate());
					assignedAssetsEntity.setStatus("Assigned");
					assignedAssetsEntity.setType(asset.getType());
					assignedAssetsEntity.setSerialNumber(asset.getSerialNumber());
					asset.setStatus("Assigned");
					asset.setAssignedDate(assignableAssetDto.getAssignedDate());
					asset.setAssignedBy(assignableAssetDto.getAssignedBy());
					asset.setEmpId(assignableAssetDto.getEmpId());
					for(CountOfAssets i:countOfAssets) {
						if(asset.getLocation().equalsIgnoreCase(i.getLocation())) {
							if(asset.getAssetName().equalsIgnoreCase("Laptop")) {
								i.setUnAssignedLaptopCount(i.getUnAssignedLaptopCount()-1);
								assetCountRepository.save(i);
							}
							if(asset.getAssetName().equalsIgnoreCase("Mouse")) {
								i.setUnAssignedMouseCount(i.getUnAssignedMouseCount()-1);
								assetCountRepository.save(i);
							}
							if(asset.getAssetName().equalsIgnoreCase("LaptopCharger")) {
								i.setUnAssignedLaptopChargerCount(i.getUnAssignedLaptopChargerCount()-1);
								assetCountRepository.save(i);
							}
							if(asset.getAssetName().equalsIgnoreCase("HaedPhone")) {
								i.setUnAssignedHeadphonesCount(i.getUnAssignedHeadphonesCount()-1);
								assetCountRepository.save(i);
							}
							if(asset.getAssetName().equalsIgnoreCase("Bag")) {
								i.setUnAssignedBagCount(i.getUnAssignedBagCount()-1);
								assetCountRepository.save(i);
							}





							if(asset.getAssetName().equalsIgnoreCase("DataCard")) {
								i.setUnAssignedDataCardCount(i.getUnAssignedDataCardCount()-1);
								assetCountRepository.save(i);
							}

							if(asset.getAssetName().equalsIgnoreCase("Mobile")) {
								i.setUnAssignedMobileCount(i.getUnAssignedMobileCount()-1);
								assetCountRepository.save(i);
							}
							if(asset.getAssetName().equalsIgnoreCase("Camera")) {
								i.setUnAssignedCameraCount(i.getUnAssignedCameraCount()-1);
								assetCountRepository.save(i);
							}
							if(asset.getAssetName().equalsIgnoreCase("Projector")) {
								i.setUnAssignedProjectorCount(i.getUnAssignedProjectorCount()-1);
								assetCountRepository.save(i);
							}
							if(asset.getAssetName().equalsIgnoreCase("FireWall")) {
								i.setUnAssignedFireWallCount(i.getUnAssignedFireWallCount()-1);
								assetCountRepository.save(i);
							}
							if(asset.getAssetName().equalsIgnoreCase("Switch")) {
								i.setUnAssignedSwitchCount(i.getUnAssignedSwitchCount()-1);
								assetCountRepository.save(i);
							}
							if(asset.getAssetName().equalsIgnoreCase("DVR")) {
								i.setUnAssignedDvrCount(i.getUnAssignedDvrCount()-1);
								assetCountRepository.save(i);
							}
							if(asset.getAssetName().equalsIgnoreCase("Speaker")) {
								i.setUnAssignedSpeakerCount(i.getUnAssignedSpeakerCount()-1);
								assetCountRepository.save(i);
							}



							assetCountRepository.save(i);

						}
					}

					assignedAssetsRepository.save(assignedAssetsEntity);
					return ResponseEntity.ok(HttpStatus.OK);
				}

				else
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Asset was in Scrap");
			} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("asset");
			}
		}
		else {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("emp");
		}


	}

	@Override
	@Async
	public ResponseEntity<?> getRecentAssigned() {
		RecentAssignedEmp recent=new RecentAssignedEmp();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		List<EmployeeEntity> emps=new ArrayList<>();
		List<RecentAssignedEmp> recentAssignedEmpSet=new ArrayList<>();
		List<AssignedAssetsEntity> set= assignedAssetsRepository.findByEmpIdNotNull().stream().filter(x-> {
			try {
				return (DAYS.between(sf.parse(x.getAssignedDate().toString()).toInstant(),new Date().toInstant()))<15;
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}).collect(Collectors.toList());
		set.forEach(x->emps.add(employeeRepository.findByEmpId(x.getEmpId())));
		List<EmployeeEntity> uniqEmp=new ArrayList<>(new HashSet<>(emps));
		return  ResponseEntity.ok().body(uniqEmp);
	}
	public List<AssignedAssetsEntity> getAllAssetsAssignedToParticularEmployee(String empId){
		List<AssignedAssetsEntity> assetsEntities= assignedAssetsRepository.findByEmpId(empId);
		return assetsEntities;
	}

}