package com.denis.data.entity.mapper;

import com.denis.data.entity.IncomeCategoryEntity;
import com.denis.domain.models.IncomeCategory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class IncomeCategoryDataMapper {

    @Inject
    public IncomeCategoryDataMapper() {
    }

    public IncomeCategory fromEntity(IncomeCategoryEntity incomeCategoryEntity) {
        IncomeCategory incomeCategory = null;
        if (incomeCategoryEntity != null) {
            incomeCategory = new IncomeCategory(incomeCategoryEntity.getId());
            incomeCategory.setName(incomeCategoryEntity.getName());
            incomeCategory.setPath(incomeCategoryEntity.getPath());
        }
        return incomeCategory;
    }

    public List<IncomeCategory> fromEntity(List<IncomeCategoryEntity> profitCategoryEntities){
        List<IncomeCategory> profitCategories = null;
        if(profitCategoryEntities != null && !profitCategoryEntities.isEmpty()){
            profitCategories = new ArrayList<>();
            for (IncomeCategoryEntity pce : profitCategoryEntities) {
                IncomeCategory pc = fromEntity(pce);
                profitCategories.add(pc);
            }
        }
        return profitCategories;
    }

    public IncomeCategoryEntity toEntity(IncomeCategory entity){
        IncomeCategoryEntity pce = null;
        if(entity != null){
            pce = new IncomeCategoryEntity(entity.getId());
            pce.setName(entity.getName());
            pce.setPath(entity.getPath());
        }
        return pce;
    }

    public List<IncomeCategoryEntity> toEntity(Collection<IncomeCategory> profitCategories){
        List<IncomeCategoryEntity> entities = null;
        if(profitCategories != null && !profitCategories.isEmpty()){
            entities = new ArrayList<>();
            for (IncomeCategory pc : profitCategories) {
                IncomeCategoryEntity pce = toEntity(pc);
                entities.add(pce);
            }
        }
        return entities;
    }

}
