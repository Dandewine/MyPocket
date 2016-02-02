package com.denis.data.entity.mapper;

import com.denis.data.entity.CircleOperationEntity;
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

    public CircleOperation transform(CircleOperationEntity entity) {
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

    public List<CircleOperation> transform(List<CircleOperationEntity> entityList) {
        List<CircleOperation> operationList = null;
        if (entityList != null && !entityList.isEmpty()) {
            operationList = new ArrayList<>();
            for (CircleOperationEntity entity : entityList) {
                CircleOperation operation = transform(entity);
                operationList.add(operation);
            }
        }
        return operationList;
    }

    public CircleOperationEntity transform(CircleOperation operation) {
        CircleOperationEntity entity = null;
        if (operation != null) {
            entity = new CircleOperationEntity(operation.getId());
            entity.setInterval(operation.getInterval().getValue());
            entity.setTransactionEntity(transactionDataMapper.transform(operation.getTransactionEntity()));
        }
        return entity;
    }

    public List<CircleOperationEntity> transform(Collection<CircleOperation> operations) {
        List<CircleOperationEntity> entities = null;
        if (operations != null && !operations.isEmpty()) {
            entities = new ArrayList<>();
            for (CircleOperation co : operations) {
                CircleOperationEntity coe = transform(co);
                entities.add(coe);
            }
        }
        return entities;
    }
}
