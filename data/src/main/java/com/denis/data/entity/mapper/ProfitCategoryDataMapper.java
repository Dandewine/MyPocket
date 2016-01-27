package com.denis.data.entity.mapper;

import com.denis.data.entity.IncomeCategoryEntity;
import com.denis.domain.models.ProfitCategory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class ProfitCategoryDataMapper {

    @Inject
    public ProfitCategoryDataMapper() {
    }

    public ProfitCategory transform(IncomeCategoryEntity incomeCategoryEntity) {
        ProfitCategory profitCategory = null;
        if (incomeCategoryEntity != null) {
            profitCategory = new ProfitCategory(incomeCategoryEntity.getId());
            profitCategory.setName(incomeCategoryEntity.getName());
            profitCategory.setPath(incomeCategoryEntity.getPath());
        }
        return profitCategory;
    }

    public List<ProfitCategory> transform(List<IncomeCategoryEntity> profitCategoryEntities){
        List<ProfitCategory> profitCategories = null;
        if(profitCategoryEntities != null && !profitCategoryEntities.isEmpty()){
            profitCategories = new ArrayList<>();
            for (IncomeCategoryEntity pce : profitCategoryEntities) {
                ProfitCategory pc = transform(pce);
                profitCategories.add(pc);
            }
        }
        return profitCategories;
    }

    public IncomeCategoryEntity transform(ProfitCategory entity){
        IncomeCategoryEntity pce = null;
        if(entity != null){
            pce = new IncomeCategoryEntity(entity.getId());
            pce.setName(entity.getName());
            pce.setPath(entity.getPath());
        }
        return pce;
    }

    public List<IncomeCategoryEntity> transform(Collection<ProfitCategory> profitCategories){
        List<IncomeCategoryEntity> entities = null;
        if(profitCategories != null && !profitCategories.isEmpty()){
            entities = new ArrayList<>();
            for (ProfitCategory pc : profitCategories) {
                IncomeCategoryEntity pce = transform(pc);
                entities.add(pce);
            }
        }
        return entities;
    }

}
