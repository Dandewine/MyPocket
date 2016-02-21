package com.denis.data.entity.mapper;

import com.denis.data.entity.CycleOperationEntity;
import com.denis.domain.models.CycleOperation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CycleOperationDataMapper implements EntityMapper<CycleOperationEntity, CycleOperation> {

    private TransactionDataMapper transactionDataMapper;

    @Inject
    public CycleOperationDataMapper(TransactionDataMapper transactionDataMapper) {
        this.transactionDataMapper = transactionDataMapper;
    }

    @Override
    public CycleOperation fromEntity(CycleOperationEntity entity) {
        CycleOperation operation = null;
        if (entity != null) {
            operation = new CycleOperation(entity.getId());
            operation.setInterval(entity.getInterval());
            operation.setName(entity.getName());
            operation.setTransaction(
                    transactionDataMapper.fromEntity(entity.getTransactionEntity())
            );
            operation.setTriggerTime(entity.getTriggerTime());
        }
        return operation;
    }

    @Override
    public List<CycleOperation> fromEntity(List<CycleOperationEntity> entityList) {
        List<CycleOperation> operationList = null;
        if (entityList != null && !entityList.isEmpty()) {
            operationList = new ArrayList<>();
            for (CycleOperationEntity entity : entityList) {
                CycleOperation operation = fromEntity(entity);
                operationList.add(operation);
            }
        }
        return operationList;
    }

    @Override
    public CycleOperationEntity toEntity(CycleOperation operation) {
        CycleOperationEntity entity = null;
        if (operation != null) {
            entity = new CycleOperationEntity(operation.getId());
            entity.setInterval(operation.getInterval());
            entity.setTransactionEntity(transactionDataMapper.toEntity(operation.getTransaction()));
            entity.setName(operation.getName());
            entity.setTriggerTime(operation.getTriggerTime());
        }
        return entity;
    }


    @Override
    public List<CycleOperationEntity> toEntity(List<CycleOperation> cycleOperations) {
        List<CycleOperationEntity> entities = null;
        if (cycleOperations != null && !cycleOperations.isEmpty()) {
            entities = new ArrayList<>();
            for (CycleOperation co : cycleOperations) {
                CycleOperationEntity coe = toEntity(co);
                entities.add(coe);
            }
        }
        return entities;
    }
}
