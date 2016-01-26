package com.denis.data.entity.mapper;

import com.denis.data.entity.WasteCategoryEntity;
import com.denis.domain.models.WasteCategory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class WasteCategoryDataMapper {

    @Inject
    public WasteCategoryDataMapper() {
    }

    public WasteCategory transform(WasteCategoryEntity wasteCategoryEntity) {
        WasteCategory wasteCategory = null;
        if (wasteCategoryEntity != null) {
            wasteCategory = new WasteCategory(wasteCategoryEntity.getId());
            wasteCategory.setName(wasteCategoryEntity.getName());
            wasteCategory.setPath(wasteCategoryEntity.getPath());
        }
        return wasteCategory;
    }

    public List<WasteCategory> transform(Collection<WasteCategoryEntity> wasteCategoryEntities){
        List<WasteCategory> wasteCategories = null;
        if(wasteCategoryEntities != null && !wasteCategoryEntities.isEmpty()){
            wasteCategories = new ArrayList<>();
            for (WasteCategoryEntity wce:wasteCategoryEntities){
                WasteCategory wc = transform(wce);
                wasteCategories.add(wc);
            }
        }
        return wasteCategories;
    }

    public WasteCategoryEntity transform(WasteCategory wasteCategory){
        WasteCategoryEntity wasteCategoryEntity = null;
        if(wasteCategory != null){
            wasteCategoryEntity = new WasteCategoryEntity(wasteCategory.getId());
            wasteCategoryEntity.setPath(wasteCategory.getName());
            wasteCategoryEntity.setName(wasteCategory.getName());
        }
        return wasteCategoryEntity;
    }


    public List<WasteCategoryEntity> transform(List<WasteCategory> wasteCategories){
        List<WasteCategoryEntity> wasteCategoriesEntities = null;
        if(wasteCategories != null && !wasteCategories.isEmpty()){
            wasteCategoriesEntities = new ArrayList<>();
            for (WasteCategory wc:wasteCategories){
                WasteCategoryEntity wce = transform(wc);
                wasteCategoriesEntities.add(wce);
            }
        }
        return wasteCategoriesEntities;
    }
}
