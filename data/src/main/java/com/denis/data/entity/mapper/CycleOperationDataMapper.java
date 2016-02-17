package com.denis.data.entity.mapper;

import com.denis.data.entity.CycleOperationEntity;
import com.denis.domain.models.CycleOperation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class CycleOperationDataMapper {

    private TransactionDataMapper transactionDataMapper;

    @Inject
    public CycleOperationDataMapper(TransactionDataMapper transactionDataMapper) {
        this.transactionDataMapper = transactionDataMapper;
    }

    public CycleOperation transform(CycleOperationEntity entity) {
        CycleOperation operation = null;
        if (entity != null) {
            operation = new CycleOperation(entity.getId());
            operation.setInterval(entity.getInterval());
            operation.setName(entity.getName());
            operation.setTransaction(
                    transactionDataMapper.transform(entity.getTransactionEntity())
            );
            operation.setTriggerTime(entity.getTriggerTime());
        }
        return operation;
    }

    public List<CycleOperation> transform(List<CycleOperationEntity> entityList) {
        List<CycleOperation> operationList = null;
        if (entityList != null && !entityList.isEmpty()) {
            operationList = new ArrayList<>();
            for (CycleOperationEntity entity : entityList) {
                CycleOperation operation = transform(entity);
                operationList.add(operation);
            }
        }
        return operationList;
    }

    public CycleOperationEntity transform(CycleOperation operation) {
        CycleOperationEntity entity = null;
        if (operation != null) {
            entity = new CycleOperationEntity(operation.getId());
            entity.setInterval(operation.getInterval());
            entity.setTransactionEntity(transactionDataMapper.transform(operation.getTransaction()));
            entity.setName(operation.getName());
            entity.setTriggerTime(operation.getTriggerTime());
        }
        return entity;
    }

    public List<CycleOperationEntity> transform(Collection<CycleOperation> operations) {
        List<CycleOperationEntity> entities = null;
        if (operations != null && !operations.isEmpty()) {
            entities = new ArrayList<>();
            for (CycleOperation co : operations) {
                CycleOperationEntity coe = transform(co);
                entities.add(coe);
            }
        }
        return entities;
    }
}
