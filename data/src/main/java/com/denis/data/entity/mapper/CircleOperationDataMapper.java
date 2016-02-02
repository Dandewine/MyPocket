package com.denis.data.entity.mapper;

import com.denis.data.entity.CycleOperationEntity;
import com.denis.domain.models.CircleOperation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class CircleOperationDataMapper {

    private TransactionDataMapper transactionDataMapper;

    @Inject
    public CircleOperationDataMapper(TransactionDataMapper transactionDataMapper) {
        this.transactionDataMapper = transactionDataMapper;
    }

    public CircleOperation transform(CycleOperationEntity entity) {
        CircleOperation operation = null;
        if (entity != null) {
            operation = new CircleOperation(entity.getId());
            operation.setInterval(entity.getInterval());
            operation.setTransactionEntity(
                    transactionDataMapper.transform(entity.getTransactionEntity())
            );
        }
        return operation;
    }

    public List<CircleOperation> transform(List<CycleOperationEntity> entityList) {
        List<CircleOperation> operationList = null;
        if (entityList != null && !entityList.isEmpty()) {
            operationList = new ArrayList<>();
            for (CycleOperationEntity entity : entityList) {
                CircleOperation operation = transform(entity);
                operationList.add(operation);
            }
        }
        return operationList;
    }

    public CycleOperationEntity transform(CircleOperation operation) {
        CycleOperationEntity entity = null;
        if (operation != null) {
            entity = new CycleOperationEntity(operation.getId());
            entity.setInterval(operation.getInterval().getValue());
            entity.setTransactionEntity(transactionDataMapper.transform(operation.getTransactionEntity()));
        }
        return entity;
    }

    public List<CycleOperationEntity> transform(Collection<CircleOperation> operations) {
        List<CycleOperationEntity> entities = null;
        if (operations != null && !operations.isEmpty()) {
            entities = new ArrayList<>();
            for (CircleOperation co : operations) {
                CycleOperationEntity coe = transform(co);
                entities.add(coe);
            }
        }
        return entities;
    }
}
