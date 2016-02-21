package com.denis.data.entity.mapper;

import com.denis.data.entity.MoneyBoxEntity;
import com.denis.domain.models.MoneyBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by denis on 2/21/16.
 */
public class MoneyBoxMapper implements EntityMapper<MoneyBoxEntity,MoneyBox> {
    @Override
    public MoneyBoxEntity toEntity(MoneyBox moneyBox) {
        MoneyBoxEntity entity = null;
        if(moneyBox != null){
            entity = new MoneyBoxEntity(moneyBox.getId());
            entity.setEndDate(moneyBox.getEndDate());
            entity.setStartDate(moneyBox.getStartDate());
            entity.setName(moneyBox.getName());
            entity.setCurrentBalance(moneyBox.getCurrentBalance());
            entity.setTargetBalance(moneyBox.getTargetBalance());
        }
        return entity;
    }

    @Override
    public List<MoneyBoxEntity> toEntity(List<MoneyBox> moneyBoxes) {
        List<MoneyBoxEntity> moneyBoxEntities = null;
        if(moneyBoxes != null && !moneyBoxes.isEmpty()){
            moneyBoxEntities = new ArrayList<>();
            for (MoneyBox mb : moneyBoxes) {
                MoneyBoxEntity mbe = toEntity(mb);
                moneyBoxEntities.add(mbe);
            }
        }
        return moneyBoxEntities;
    }

    @Override
    public MoneyBox fromEntity(MoneyBoxEntity model) {
        MoneyBox moneyBox = null;
        if(model != null){
            moneyBox = new MoneyBox(model.getId());
            moneyBox.setName(model.getName());
            moneyBox.setStartDate(model.getStartDate());
            moneyBox.setEndDate(model.getEndDate());
            moneyBox.setCurrentBalance(model.getCurrentBalance());
            model.setTargetBalance(model.getTargetBalance());
        }
        return moneyBox;
    }

    @Override
    public List<MoneyBox> fromEntity(List<MoneyBoxEntity> models) {
        List<MoneyBox> boxList = null;
        if(models != null && !models.isEmpty()){
            boxList = new ArrayList<>();
            for (MoneyBoxEntity mbe : models) {
                MoneyBox mb = fromEntity(mbe);
                boxList.add(mb);
            }
        }
        return boxList;
    }
}
