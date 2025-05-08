package com.verinite.assetmangementtool.controller;

import com.verinite.assetmangementtool.dto.AssignableAssetDto;
import com.verinite.assetmangementtool.entity.AssetsEntity;
import com.verinite.assetmangementtool.entity.AssignedAssetsEntity;
import com.verinite.assetmangementtool.service.AssignedAssetsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assetManager/v1/admin/")
@CrossOrigin(origins = "http://localhost:4200")
public class AssignedAssetsController {
	
	@Autowired
	private AssignedAssetsServiceImpl assignedAssetsService;
	
	@PostMapping("assignable/save")
	public Object addAssignedAssets(@RequestBody AssignableAssetDto assignableAssetDto ) {
		System.out.println(assignableAssetDto.getAssignedDate());
		return assignedAssetsService.save(assignableAssetDto);
	}
	
	@GetMapping("get-assigned-assets/{assignedId}")
	public AssignedAssetsEntity getAssignedAssetsById(@PathVariable("assignedId") int assignedId) {
		return assignedAssetsService.getAssignedAssetsById(assignedId);	
	}
	
	@GetMapping("get-assigned-assets/{assetId}")
	public AssignedAssetsEntity getAssignedAssetsByAssetId(@PathVariable("assetId") int assetId) {
		return assignedAssetsService.getAssignedAssetsByAssetsId(assetId);		
	}
	
	@GetMapping("getall/assigend/assets")
	public List<AssignedAssetsEntity> getAllAssignedAssets(){
		return assignedAssetsService.getAllassignedAssets();		
	}
	
	@PutMapping("update-assigned-assets/{assignedId}")
	public AssignedAssetsEntity updateAssignedAssets( @PathVariable("assignedId") int assignedId, @RequestBody AssignedAssetsEntity assignedAssetsEntity) {
		return assignedAssetsService.updateAssignedAssets(assignedId,assignedAssetsEntity);
	}
	
	@DeleteMapping("update-assigned-assets/{assignedId}")
	public String deleteAssignedAssets( @PathVariable("assignedId") int assignedId) {
		return assignedAssetsService.deleteAssignedAssets(assignedId);
	}
	@GetMapping("get-recent-assigned")
	public ResponseEntity<?> getRecentAssigned(){
		return assignedAssetsService.getRecentAssigned();
	}
	@GetMapping("get/all/assigned/assets/by/{empId}")
	public List<AssignedAssetsEntity> getAllByAssignedAssetsById(@PathVariable String empId){
		return assignedAssetsService.getAllAssetsAssignedToParticularEmployee(empId);
	}
}
