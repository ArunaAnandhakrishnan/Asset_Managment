package com.verinite.assetmangementtool.controller;

import com.verinite.assetmangementtool.entity.CountOfAssets;
import com.verinite.assetmangementtool.service.CountOFAssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/assetManager/v1/")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CountOfAssetsController {
	@Autowired
	CountOFAssetsService countOFAssetsService;
	@PostMapping("save/count")
	public CountOfAssets saveCount(@RequestBody CountOfAssets countOfAssets) {
		return countOFAssetsService.postAssestCount(countOfAssets);
	}
	@GetMapping("get/laptopcount/{id}")
	public int getLaptopCount(@PathVariable String id) {
		return countOFAssetsService.getLaptopCount(id);
	}
	@GetMapping("Assetcount/getall")
	public List<CountOfAssets> getAll() {
		return countOFAssetsService.getAll();
	}
	@PutMapping("update/count/{id}")
	public Object updateCount(@PathVariable String id,@RequestBody CountOfAssets countOfAssets) {
		return  countOFAssetsService.updateAssetCount(id, countOfAssets);
	}
	@GetMapping("count/totallaptops")
	public int getAllLaptopCount() {
		return countOFAssetsService.totalLaptops();
	}

}
