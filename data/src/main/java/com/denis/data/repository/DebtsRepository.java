package com.denis.data.repository;

import com.denis.data.entity.DebtEntity;
import com.denis.data.entity.mapper.DebtsMapper;
import com.denis.data.repository.datasource.interfaces.DebtsDataStore;
import com.denis.domain.models.Debt;
import com.denis.domain.repository.DebtRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;

/**
 * Created by denis on 2/20/16.
 */
public class DebtsRepository implements DebtRepository {

    private DebtsMapper debtsMapper;
    private DebtsDataStore debtsDataStore;

    @Inject
    public DebtsRepository(DebtsMapper debtsMapper, DebtsDataStore debtsDataStore) {
        this.debtsMapper = debtsMapper;
        this.debtsDataStore = debtsDataStore;
    }

    @Override
    public Observable<List<Debt>> getDebtList() {
        return debtsDataStore.getListExpenseEntities().map(debtsMapper::transform);
    }

    @Override
    public Observable<Debt> getDebt(int userId) {
        return debtsDataStore.getDebtEntity(userId).map(debtsMapper::transform);
    }

    @Override
    public Observable<Debt> addDebt(Debt debt) {
        return debtsDataStore.put(debtsMapper.transform(debt)).map(debtsMapper::transform);
    }

    @Override
    public Observable<List<Debt>> addDebt(List<Debt> debts) {
        return debtsDataStore.put(debtsMapper.transform(debts)).map(debtsMapper::transform);
    }
}
