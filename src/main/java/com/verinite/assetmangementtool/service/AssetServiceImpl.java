package com.verinite.assetmangementtool.service;

import com.verinite.assetmangementtool.dto.AssetCounterDto;
import com.verinite.assetmangementtool.entity.AssetsEntity;
import com.verinite.assetmangementtool.entity.AssetsHistoryEntity;
import com.verinite.assetmangementtool.entity.AssignedAssetsEntity;
import com.verinite.assetmangementtool.entity.CountOfAssets;
import com.verinite.assetmangementtool.repository.AssetCountRepository;
import com.verinite.assetmangementtool.repository.AssetsHistoryRepository;
import com.verinite.assetmangementtool.repository.AssetsRepository;
import com.verinite.assetmangementtool.repository.AssignedAssetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class AssetServiceImpl implements AssetService,ApplicationRunner {
    //private static final Logger logger = LogManager.getLogger(AssetmanagementtoolApplication.class);

    @Autowired
    AssetsRepository assetRepo;
    @Autowired
    AssetCountRepository assetCountRepository;;
    @Autowired
    AssetsHistoryRepository assetsHistoryRepository;
    @Autowired
    AssignedAssetsRepository assignedAssetsRepository;

    @Override
    public Object saveAsset(AssetsEntity asset) {
        asset.setStatus("UnAssigned");
        int count=0;

//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        List<CountOfAssets> countOfAssets=assetCountRepository.findAll();
        CountOfAssets countOfAssets2=new CountOfAssets();
        CountOfAssets countOfAssets3=new CountOfAssets();
        if(countOfAssets.isEmpty()) {
            countOfAssets3.setLocation(asset.getLocation());
            assetCountRepository.save(countOfAssets3);
            countOfAssets.add(countOfAssets3);
        }
        for(CountOfAssets i:countOfAssets) {
            if(asset.getLocation().equalsIgnoreCase(i.getLocation())) {
                count+=1;
            }
        }
        if(count==0) {
            countOfAssets2.setLocation(asset.getLocation());
            assetCountRepository.save(countOfAssets2);
            countOfAssets.add(countOfAssets2);
            count+=1;
            System.out.println(countOfAssets.size());
        }
        try {
            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(asset.getPurchaseDate());
            for(CountOfAssets i:countOfAssets) {
                if(i.getLocation().equalsIgnoreCase(asset.getLocation())){
                    if(asset.getAssetName().equalsIgnoreCase("Laptop")) {
                        i.setLaptopCount(i.getLaptopCount()+1);
                        i.setUnAssignedLaptopCount(i.getUnAssignedLaptopCount()+1);
                    }
                    if(asset.getAssetName().equalsIgnoreCase("Mouse")) {
                        i.setMouseCount(i.getMouseCount()+1);
                        i.setUnAssignedMouseCount(i.getUnAssignedMouseCount()+1);
                        assetCountRepository.save(i);
                    }
                    if(asset.getAssetName().equalsIgnoreCase("LaptopCharger")) {
                        i.setLaptopChargerCount(i.getLaptopChargerCount()+1);
                        i.setUnAssignedLaptopChargerCount(i.getUnAssignedLaptopChargerCount()+1);
                        assetCountRepository.save(i);
                    }
                    if(asset.getAssetName().equalsIgnoreCase("HaedPhone")) {
                        i.setHeadPhonesCount(i.getHeadPhonesCount()+1);
                        i.setUnAssignedHeadphonesCount(i.getUnAssignedHeadphonesCount()+1);
                        assetCountRepository.save(i);
                    }
                    if(asset.getAssetName().equalsIgnoreCase("Bag")) {
                        i.setBagCount(i.getBagCount()+1);
                        i.setUnAssignedBagCount(i.getUnAssignedBagCount()+1);
                        assetCountRepository.save(i);
                    }




                    if(asset.getAssetName().equalsIgnoreCase("DataCard")) {
                        i.setDataCardCount(i.getDataCardCount()+1);
                        i.setUnAssignedDataCardCount(i.getUnAssignedDataCardCount()+1);
                        assetCountRepository.save(i);
                    }

                    if(asset.getAssetName().equalsIgnoreCase("Mobile")) {
                        i.setMobileCount(i.getMobileCount()+1);
                        i.setUnAssignedMobileCount(i.getUnAssignedMobileCount()+1);
                        assetCountRepository.save(i);
                    }
                    if(asset.getAssetName().equalsIgnoreCase("Camera")) {
                        i.setCameraCount(i.getCameraCount()+1);
                        i.setUnAssignedCameraCount(i.getUnAssignedCameraCount()+1);
                        assetCountRepository.save(i);
                    }
                    if(asset.getAssetName().equalsIgnoreCase("Projector")) {
                        i.setProjectorCount(i.getProjectorCount()+1);
                        i.setUnAssignedProjectorCount(i.getUnAssignedProjectorCount()+1);
                        assetCountRepository.save(i);
                    }
                    if(asset.getAssetName().equalsIgnoreCase("Firewall")) {
                        i.setFireWallCount(i.getFireWallCount()+1);
                        i.setUnAssignedFireWallCount(i.getUnAssignedFireWallCount()+1);
                        assetCountRepository.save(i);
                    }
                    if(asset.getAssetName().equalsIgnoreCase("Switch")) {
                        i.setSwitchCount(i.getSwitchCount()+1);
                        i.setUnAssignedSwitchCount(i.getUnAssignedSwitchCount()+1);
                        assetCountRepository.save(i);
                    }
                    if(asset.getAssetName().equalsIgnoreCase("DVR")) {
                        i.setDvrCount(i.getDvrCount()+1);
                        i.setUnAssignedDvrCount(i.getUnAssignedDvrCount()+1);
                        assetCountRepository.save(i);
                    }
                    if(asset.getAssetName().equalsIgnoreCase("Speaker")) {
                        i.setSpeakerCount(i.getSpeakerCount()+1);
                        i.setUnAssignedSpeakerCount(i.getUnAssignedSpeakerCount()+1);
                        assetCountRepository.save(i);
                    }

                    assetCountRepository.save(i);

                }
            }
        }

        catch (ParseException e) {
            e.printStackTrace();
        }
        return assetRepo.save(asset);
    }

//    public void saveWithoutReturn(AssetsEntity asset) {
//        asset.setStatus("UnAssigned");
//        int count=0;
//
////        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        List<CountOfAssets> countOfAssets=assetCountRepository.findAll();
//        CountOfAssets countOfAssets2=new CountOfAssets();
//        CountOfAssets countOfAssets3=new CountOfAssets();
//        if(countOfAssets.isEmpty()) {
//            countOfAssets3.setLocation(asset.getLocation());
//            assetCountRepository.save(countOfAssets3);
//            countOfAssets.add(countOfAssets3);
//        }
//        for(CountOfAssets i:countOfAssets) {
//            if(asset.getLocation().equalsIgnoreCase(i.getLocation())) {
//                count+=1;
//            }
//        }
//        if(count==0) {
//            countOfAssets2.setLocation(asset.getLocation());
//            assetCountRepository.save(countOfAssets2);
//            countOfAssets.add(countOfAssets2);
//            count+=1;
//            System.out.println(countOfAssets.size());
//        }
//        try {
//            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(asset.getPurchaseDate());
//            for(CountOfAssets i:countOfAssets) {
//                if(i.getLocation().equalsIgnoreCase(asset.getLocation())){
//                    if(asset.getAssetName().equalsIgnoreCase("Laptop")) {
//                        System.out.println(asset.getAssetName()+" innner method ");
//                        i.setLaptopCount(i.getLaptopCount()+1);
//                        i.setUnAssignedLaptopCount(i.getUnAssignedLaptopCount()+1);
//                    }
//                    if(asset.getAssetName().equalsIgnoreCase("Mouse")) {
//                        i.setMouseCount(i.getMouseCount()+1);
//                        i.setUnAssignedMouseCount(i.getUnAssignedMouseCount()+1);
//                        assetCountRepository.save(i);
//                    }
//                    if(asset.getAssetName().equalsIgnoreCase("LaptopCharger")) {
//                        i.setLaptopChargerCount(i.getLaptopChargerCount()+1);
//                        i.setUnAssignedLaptopChargerCount(i.getUnAssignedLaptopChargerCount()+1);
//                        assetCountRepository.save(i);
//                    }
//                    if(asset.getAssetName().equalsIgnoreCase("HaedPhone")) {
//                        i.setHeadPhonesCount(i.getHeadPhonesCount()+1);
//                        i.setUnAssignedHeadphonesCount(i.getUnAssignedHeadphonesCount()+1);
//                        assetCountRepository.save(i);
//                    }
//                    if(asset.getAssetName().equalsIgnoreCase("Bag")) {
//                        i.setBagCount(i.getBagCount()+1);
//                        i.setUnAssignedBagCount(i.getUnAssignedBagCount()+1);
//                        assetCountRepository.save(i);
//                    }
//
//
//
//
//                    if(asset.getAssetName().equalsIgnoreCase("DataCard")) {
//                        i.setDataCardCount(i.getDataCardCount()+1);
//                        i.setUnAssignedDataCardCount(i.getUnAssignedDataCardCount()+1);
//                        assetCountRepository.save(i);
//                    }
//
//                    if(asset.getAssetName().equalsIgnoreCase("Mobile")) {
//                        i.setMobileCount(i.getMobileCount()+1);
//                        i.setUnAssignedMobileCount(i.getUnAssignedMobileCount()+1);
//                        assetCountRepository.save(i);
//                    }
//                    if(asset.getAssetName().equalsIgnoreCase("Camera")) {
//                        i.setCameraCount(i.getCameraCount()+1);
//                        i.setUnAssignedCameraCount(i.getUnAssignedCameraCount()+1);
//                        assetCountRepository.save(i);
//                    }
//                    if(asset.getAssetName().equalsIgnoreCase("Projector")) {
//                        i.setProjectorCount(i.getProjectorCount()+1);
//                        i.setUnAssignedProjectorCount(i.getUnAssignedProjectorCount()+1);
//                        assetCountRepository.save(i);
//                    }
//                    if(asset.getAssetName().equalsIgnoreCase("Firewall")) {
//                        i.setFireWallCount(i.getFireWallCount()+1);
//                        i.setUnAssignedFireWallCount(i.getUnAssignedFireWallCount()+1);
//                        assetCountRepository.save(i);
//                    }
//                    if(asset.getAssetName().equalsIgnoreCase("Switch")) {
//                        i.setSwitchCount(i.getSwitchCount()+1);
//                        i.setUnAssignedSwitchCount(i.getUnAssignedSwitchCount()+1);
//                        assetCountRepository.save(i);
//                    }
//                    if(asset.getAssetName().equalsIgnoreCase("DVR")) {
//                        i.setDvrCount(i.getDvrCount()+1);
//                        i.setUnAssignedDvrCount(i.getUnAssignedDvrCount()+1);
//                        assetCountRepository.save(i);
//                    }
//                    if(asset.getAssetName().equalsIgnoreCase("Speaker")) {
//                        i.setSpeakerCount(i.getSpeakerCount()+1);
//                        i.setUnAssignedSpeakerCount(i.getUnAssignedSpeakerCount()+1);
//                        assetCountRepository.save(i);
//                    }
//
//                    assetCountRepository.save(i);
//
//                }
//            }
//        }
//
//        catch (ParseException e) {
//            e.printStackTrace();
//        }
//        assetRepo.save(asset);
//
//    }

    @Override
    public List<AssetsEntity> getAllAssets() {

        return assetRepo.findAll();
    }

    @Override
    public Object getAssetBySerialNumber(String id) {
        try {
            AssetsEntity asset=assetRepo.findBySerialNumber(id);
            if(!asset.getStatus().equalsIgnoreCase("scrap"))
                return asset;
            else {
                return "In Scrap";
            }
        } catch (NoSuchElementException e) {
            return "id Not Found";
        }



    }

    @Override
    public Object updateAsset(AssetsEntity asset) {
        System.out.println(asset.getSerialNumber());
        try {
            AssetsEntity new_asset=assetRepo.findBySerialNumber(asset.getSerialNumber());
            System.out.println(new_asset.getSerialNumber());
            if(!new_asset.getStatus().equalsIgnoreCase("Scrap")) {
                if(asset.getAssetName()!=null)
                    new_asset.setAssetName(asset.getAssetName());
                if(asset.getEmpId()!=null)
                    new_asset.setEmpId(asset.getEmpId());
                if(asset.getPurchaseDate()!=null)
                    new_asset.setPurchaseDate(asset.getPurchaseDate());
                if(asset.getWarrantyDate()!=null)
                    new_asset.setWarrantyDate(asset.getWarrantyDate());
                if(asset.getSerialNumber()!=null)
                    new_asset.setSerialNumber(asset.getSerialNumber());
                if(asset.getStatus()!=null)
                    new_asset.setStatus(asset.getStatus());
                if(asset.getType()!=null)
                    new_asset.setType(asset.getType());
                if(asset.getAddedBy()!=null)
                    new_asset.setAddedBy(asset.getAddedBy());
                if(asset.getOperatingSystem()!=null)
                    new_asset.setOperatingSystem(asset.getOperatingSystem());
                if(asset.getModelName()!=null)
                    new_asset.setModelName(asset.getModelName());
                return assetRepo.save(new_asset);
            }
            else
                return "The Asset Scrap";
        } catch (NoSuchElementException e) {
            return "Id not Found";
        }

    }

    @Override
    public Object deleteAsset(int id) {
        try {
            AssetsEntity asset=assetRepo.findById(id).get();
            System.out.println(asset.getAssetId());
            if(!asset.getStatus().equalsIgnoreCase("Scrap")) {
                asset.setStatus("Scrap");
                return assetRepo.save(asset);
            }
            else
                //logger.debug("Given assetId is already in scrap");
                return "Already in Scrap";
        } catch (NoSuchElementException e) {
            //logger.debug("The given assetId is not found");
            return "Id not Found";
        }


    }


    @Override
    public List<AssetsEntity> getThroughStatus(String str) {
        List<AssetsEntity> assets=get();
        List<AssetsEntity> assets2=new ArrayList<AssetsEntity>();
        for(AssetsEntity i:assets) {
            if(i.getStatus().equalsIgnoreCase(str))
                assets2.add(i);
        }
        return assets2;
    }
    public List<AssetsEntity> get(){
        return assetRepo.findAll();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

    @Override
    public int getCountOfAssigned() {
        int assigned=0;
        List<AssetsEntity> assetsEntities=assetRepo.findAll();
        for(AssetsEntity i:assetsEntities) {
            if(i.getStatus().equalsIgnoreCase("Assigned"))
                assigned+=1;
        }
        return assigned;
    }
    @Override
    public int getCountOfUnassigned() {
        int unassigned=0;
        List<AssetsEntity> assetsEntities=assetRepo.findAll();
        for(AssetsEntity i:assetsEntities) {
            if(i.getStatus().equalsIgnoreCase("UnAssigned"))
                unassigned+=1;
        }
        return unassigned;
    }
    @Override
    public int getLaptopCountByLocation(String id) {
        CountOfAssets countOfAsset=assetCountRepository.findByLocation(id);
        return countOfAsset.getLaptopCount();
    }

    @Override
    public int totalLaptops() {
        List<CountOfAssets> countOfAssets=assetCountRepository.findAll();
        int total=0;
        for(CountOfAssets i : countOfAssets) {
            total+=i.getLaptopCount();
        }
        return total;
    }
    @Override
    public int getCountOfUnassignedByLocation(String id) {
        CountOfAssets countOfAsset=assetCountRepository.findByLocation(id);
        return countOfAsset.getUnAssignedLaptopCount();
    }
    @Override
    public List<AssetCounterDto> getUnassignedAndTotalLaptops(){
        List<AssetCounterDto> assetCounterDtos =new ArrayList<AssetCounterDto>();
        AssetCounterDto temp1 = new AssetCounterDto();
        List<CountOfAssets> countOfAssets= assetCountRepository.findAll();
        countOfAssets.forEach(x->{
            temp1.setLocation(x.getLocation());
            temp1.setTotal(x.getLaptopCount());
            temp1.setUnAssigned(x.getUnAssignedLaptopCount());
            assetCounterDtos.add(temp1);
        });
        for(AssetCounterDto i :assetCounterDtos)
            System.out.println(i.getLocation());

        return assetCounterDtos;
    }

    @Override
    public List<AssetsEntity> getLaptopsUnderWarenty() {
        List<AssetsEntity> all=assetRepo.findAll();
        List<AssetsEntity> assetsEntities=new ArrayList<AssetsEntity>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        // System.out.println(dtf.format(now));
        String today=dtf.format(now);

        for(AssetsEntity i:all){
            try {
                Date date=new SimpleDateFormat("dd/MM/yyyy").parse(i.getWarrantyDate());
                Date todatDate=new SimpleDateFormat("dd/MM/yyyy").parse(today);
                if(date.compareTo(todatDate)>0) {
                    assetsEntities.add(i);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        return assetsEntities;
    }

    @Override
    public List<AssetsEntity> getLaptopsOverWarenty() {
        List<AssetsEntity> all=assetRepo.findAll();
        List<AssetsEntity> assetsEntities=new ArrayList<AssetsEntity>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        // System.out.println(dtf.format(now));
        String today=dtf.format(now);

        for(AssetsEntity i:all){
            try {
                Date date=new SimpleDateFormat("dd/MM/yyyy").parse(i.getWarrantyDate());
                Date todatDate=new SimpleDateFormat("dd/MM/yyyy").parse(today);
                if(date.compareTo(todatDate)<0) {
                    assetsEntities.add(i);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        return assetsEntities;
    }

    @Override
    public Object saveHistory(String serialNo,String empId) {
        try {
            AssignedAssetsEntity asset=assignedAssetsRepository.findBySerialNumberAndEmpId(serialNo,empId);
            AssetsHistoryEntity assetsHistoryEntity=new AssetsHistoryEntity();
            List<AssetsHistoryEntity> assetsHistoryEntities=assetsHistoryRepository.findAll();
            List<CountOfAssets> countOfAssets=assetCountRepository.findAll();
            if(!asset.getStatus().equalsIgnoreCase("scrap")) {
                assetsHistoryEntity.setAssetId(asset.getAssetId());
                assetsHistoryEntity.setAssetName(asset.getAssetName());
                assetsHistoryEntity.setEmpId(asset.getEmpId());
                assetsHistoryEntity.setLocation(asset.getLocation());
                assetsHistoryEntity.setModelName(asset.getModelName());
                assetsHistoryEntity.setOperatingSystem(asset.getOperatingSystem());
                assetsHistoryEntity.setPurchaseDate(asset.getPurchaseDate());
                assetsHistoryEntity.setWarrantyDate(asset.getWarrantyDate());
                assetsHistoryEntity.setReturnDate(new Date());
                assetsHistoryEntity.setStatus("Unassigned");
                assetsHistoryEntity.setEmpId(asset.getEmpId());
                assetsHistoryEntity.setType(asset.getType());
                assetsHistoryEntity.setSerialNumber(asset.getSerialNumber());
                assetsHistoryEntity.setAssignedBy(asset.getAssignedBy());
                assetsHistoryEntity.setAssignedDate(asset.getAssignedDate());
                asset.setEmpId(null);
                asset.setAssignedBy(null);
                asset.setAssignedDate(null);
                asset.setStatus("Unassigned");
                asset.setReturnDate(null);
                for(CountOfAssets i:countOfAssets) {
                    if(i.getLocation().equalsIgnoreCase(asset.getLocation())){
                        if(asset.getAssetName().equalsIgnoreCase("Laptop")) {
                            i.setUnAssignedLaptopCount(i.getUnAssignedLaptopCount()+1);
                            assetCountRepository.save(i);
                        }
                        if(asset.getAssetName().equalsIgnoreCase("Mouse")) {
                            i.setUnAssignedMouseCount(i.getUnAssignedMouseCount()+1);
                            assetCountRepository.save(i);
                        }
                        if(asset.getAssetName().equalsIgnoreCase("LaptopCharger")) {
                            i.setUnAssignedLaptopChargerCount(i.getUnAssignedLaptopChargerCount());
                            assetCountRepository.save(i);
                        }
                        if(asset.getAssetName().equalsIgnoreCase("HaedPhone")) {
                            i.setUnAssignedHeadphonesCount(i.getUnAssignedHeadphonesCount()+1);
                            assetCountRepository.save(i);
                        }
                        if(asset.getAssetName().equalsIgnoreCase("Bag")) {
                            i.setUnAssignedBagCount(i.getUnAssignedBagCount()+1);
                            assetCountRepository.save(i);
                        }



                        if(asset.getAssetName().equalsIgnoreCase("DataCard")) {
                            i.setUnAssignedDataCardCount(i.getUnAssignedDataCardCount()+1);
                            assetCountRepository.save(i);
                        }

                        if(asset.getAssetName().equalsIgnoreCase("Mobile")) {
                            i.setUnAssignedMobileCount(i.getUnAssignedMobileCount()+1);
                            assetCountRepository.save(i);
                        }
                        if(asset.getAssetName().equalsIgnoreCase("Camera")) {
                            i.setUnAssignedCameraCount(i.getUnAssignedCameraCount()+1);
                            assetCountRepository.save(i);
                        }
                        if(asset.getAssetName().equalsIgnoreCase("Projector")) {
                            i.setUnAssignedProjectorCount(i.getUnAssignedProjectorCount()+1);
                            assetCountRepository.save(i);
                        }
                        if(asset.getAssetName().equalsIgnoreCase("FireWall")) {
                            i.setUnAssignedFireWallCount(i.getUnAssignedFireWallCount()+1);
                            assetCountRepository.save(i);
                        }
                        if(asset.getAssetName().equalsIgnoreCase("Switch")) {
                            i.setUnAssignedSwitchCount(i.getUnAssignedSwitchCount()+1);
                            assetCountRepository.save(i);
                        }
                        if(asset.getAssetName().equalsIgnoreCase("DVR")) {
                            i.setUnAssignedDvrCount(i.getUnAssignedDvrCount()+1);
                            assetCountRepository.save(i);
                        }
                        if(asset.getAssetName().equalsIgnoreCase("Speaker")) {
                            i.setUnAssignedSpeakerCount(i.getUnAssignedSpeakerCount()+1);
                            assetCountRepository.save(i);
                        }
                        assetCountRepository.save(i);
                    }
                }
                AssetsEntity assetCopy=assetRepo.findBySerialNumber(serialNo);
                assetCopy.setStatus("unassigned");
                assetCopy.setEmpId(null);
                assetRepo.save(assetCopy);
                assignedAssetsRepository.save(asset);
                assetsHistoryEntities.add(assetsHistoryEntity);
                assetsHistoryRepository.saveAll(assetsHistoryEntities);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Serial Number not found or The Item belongs to this Serial Number is in Scrap";
        }
        System.out.println("Updated Sussfully");
        return "Updated Sussfully";
    }


    @Override
    public List<AssetsEntity> getUnAssigned() {
         List<AssetsEntity> list=assetRepo.findAll().stream().filter(x->x.getStatus().equalsIgnoreCase("unassigned")).collect(Collectors.toList());
        return list;
    }

}