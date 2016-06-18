package com.denis.data.repository;

import com.denis.data.entity.IncomeCategoryEntity;
import com.denis.data.entity.mapper.IncomeCategoryDataMapper;
import com.denis.data.repository.datasource.interfaces.IncomeCategoryDataStore;
import com.denis.domain.models.categories.Category;
import com.denis.domain.models.categories.IncomeCategory;
import com.denis.domain.repository.IncomeCategoriesRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class IncomeCategoriesDataRepository implements IncomeCategoriesRepository{
    private IncomeCategoryDataMapper mapper;
    private IncomeCategoryDataStore dataStore;

    @Inject
    public IncomeCategoriesDataRepository(IncomeCategoryDataMapper mapper,
                                          IncomeCategoryDataStore dataStore) {
        this.mapper = mapper;
        this.dataStore = dataStore;
    }

    @Override
    public Observable<List<IncomeCategory>> getIncomeCategoryList() {
        return dataStore.getListIncomeEntities()
                .map(mapper::fromEntity);
    }

    @Override
    public Observable<IncomeCategory> getIncomeCategory(String userId) {
        return dataStore.getIncomeCategoryEntity(userId)
                .map(mapper::fromEntity);
    }

    @Override
    public Observable<IncomeCategory> addIncomeCategory(IncomeCategory incomeCategory) {
        IncomeCategoryEntity entity = mapper.toEntity(incomeCategory);
        return dataStore.put(entity).map(mapper::fromEntity);

    }

    @Override
    public Observable<List<IncomeCategory>> addIncomeCategory(List<IncomeCategory> incomeCategorys) {
        List<IncomeCategoryEntity> entities = mapper.toEntity(incomeCategorys);
        return dataStore.put(entities).map(mapper::fromEntity);
    }
}
