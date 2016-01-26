package com.denis.data.entity.mapper;

import com.denis.data.entity.ProfitCategoryEntity;
import com.denis.domain.models.ProfitCategory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class ProfitCategoryDataMapper {

    @Inject
    public ProfitCategoryDataMapper() {
    }

    public ProfitCategory transform(ProfitCategoryEntity profitCategoryEntity) {
        ProfitCategory profitCategory = null;
        if (profitCategoryEntity != null) {
            profitCategory = new ProfitCategory(profitCategoryEntity.getId());
            profitCategory.setName(profitCategoryEntity.getName());
            profitCategory.setPath(profitCategoryEntity.getPath());
        }
        return profitCategory;
    }

    public List<ProfitCategory> transform(List<ProfitCategoryEntity> profitCategoryEntities){
        List<ProfitCategory> profitCategories = null;
        if(profitCategoryEntities != null && !profitCategoryEntities.isEmpty()){
            profitCategories = new ArrayList<>();
            for (ProfitCategoryEntity pce : profitCategoryEntities) {
                ProfitCategory pc = transform(pce);
                profitCategories.add(pc);
            }
        }
        return profitCategories;
    }

    public ProfitCategoryEntity transform(ProfitCategory entity){
        ProfitCategoryEntity pce = null;
        if(entity != null){
            pce = new ProfitCategoryEntity(entity.getId());
            pce.setName(entity.getName());
            pce.setPath(entity.getPath());
        }
        return pce;
    }

    public List<ProfitCategoryEntity> transfrom(Collection<ProfitCategory> profitCategories){
        List<ProfitCategoryEntity> entities = null;
        if(profitCategories != null && !profitCategories.isEmpty()){
            entities = new ArrayList<>();
            for (ProfitCategory pc : profitCategories) {
                ProfitCategoryEntity pce = transform(pc);
                entities.add(pce);
            }
        }
        return entities;
    }

}
