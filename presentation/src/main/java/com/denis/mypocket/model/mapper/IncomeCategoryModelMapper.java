package com.denis.mypocket.model.mapper;

import com.denis.domain.models.IncomeCategory;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.model.IncomeCategoryModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class IncomeCategoryModelMapper {

    @Inject
    public IncomeCategoryModelMapper() {
    }

    public IncomeCategoryModel transform(IncomeCategory category) {
        IncomeCategoryModel categoryModel = null;
        if (category != null) {
            categoryModel = new IncomeCategoryModel(category.getId());
            categoryModel.setName(category.getName());
            categoryModel.setPath(category.getPath());
        }
        return categoryModel;
    }

    public List<IncomeCategoryModel> transform(List<IncomeCategory> categoryList) {
        List<IncomeCategoryModel> modelList = null;
        if (categoryList != null && !categoryList.isEmpty()) {
            modelList = new ArrayList<>();
            for (IncomeCategory ic : categoryList) {
                IncomeCategoryModel icm = transform(ic);
                modelList.add(icm);
            }
        }
        return modelList;
    }

}