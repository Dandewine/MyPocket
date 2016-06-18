package com.denis.mypocket.model.mapper;

import com.denis.domain.models.categories.IncomeCategory;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.model.categories.IncomeCategoryModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class IncomeCategoryModelMapper implements ModelMapper<IncomeCategory, IncomeCategoryModel> {

    @Inject
    public IncomeCategoryModelMapper() {
    }

    public IncomeCategoryModel toModel(IncomeCategory category) {
        IncomeCategoryModel categoryModel = null;
        if (category != null) {
            categoryModel = new IncomeCategoryModel(category.getId());
            categoryModel.setName(category.getName());
            categoryModel.setPath(category.getPath());
        }
        return categoryModel;
    }

    @Override
    public List<IncomeCategoryModel> toModel(List<IncomeCategory> categoryList) {
        List<IncomeCategoryModel> modelList = null;
        if (categoryList != null && !categoryList.isEmpty()) {
            modelList = new ArrayList<>();
            for (IncomeCategory ic : categoryList) {
                IncomeCategoryModel icm = toModel(ic);
                modelList.add(icm);
            }
        }
        return modelList;
    }

    @Override
    public List<IncomeCategory> fromModel(List<IncomeCategoryModel> incomeCategoryModels) {
        return null;
    }

    @Override
    public IncomeCategory fromModel(IncomeCategoryModel incomeCategoryModel) {
        return null;
    }
}
