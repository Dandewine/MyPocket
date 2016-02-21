package com.denis.data.entity.mapper;

import com.denis.data.entity.DebtEntity;
import com.denis.domain.models.Debt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by denis on 2/20/16.
 */
public class DebtsMapper implements EntityMapper<DebtEntity, Debt> {

    @Inject
    public DebtsMapper() {
    }

    @Override
    public DebtEntity toEntity(Debt debt) {
        DebtEntity debtEntity = null;
        if (debt != null) {
            debtEntity = new DebtEntity(debt.getId());
            debtEntity.setAmount(debt.getAmount());
            debtEntity.setEndDate(debt.getEndDate());
            debtEntity.setName(debt.getName());
            debtEntity.setAmount(debt.getAmount());
            debtEntity.setPerson(debt.getPerson());
            debtEntity.setStartDate(debt.getStartDate());
        }
        return debtEntity;
    }

    @Override
    public List<DebtEntity> toEntity(List<Debt> debtsList) {
        List<DebtEntity> transactionList = null;
        if (debtsList != null && !debtsList.isEmpty()) {
            transactionList = new ArrayList<>();
            for (Debt debt : debtsList) {
                DebtEntity d = toEntity(debt);
                transactionList.add(d);
            }
        }
        return transactionList;
    }

    @Override
    public Debt fromEntity(DebtEntity debtEntity) {
        Debt debt = null;
        if (debtEntity != null) {
            debt = new Debt(debtEntity.getId());
            debt.setStartDate(debtEntity.getStartDate());
            debt.setEndDate(debtEntity.getEndDate());
            debt.setAmount(debtEntity.getAmount());
            debt.setPerson(debtEntity.getPerson());
            debt.setName(debt.getPerson());
        }
        return debt;
    }

    @Override
    public List<Debt> fromEntity(List<DebtEntity> debtList) {
        List<Debt> entityList = null;
        if (debtList != null && !debtList.isEmpty()) {
            entityList = new ArrayList<>();
            for (DebtEntity de : debtList) {
                Debt d = fromEntity(de);
                entityList.add(d);
            }
        }
        return entityList;
    }
}
