package com.denis.mypocket.model.mapper;

import com.denis.domain.models.CircleOperation;
import com.denis.mypocket.model.CircleOperationModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CircleOperationModelMapper {

    private TransactionModelDataMapper transactionDataMapper;

    @Inject
    public CircleOperationModelMapper(TransactionModelDataMapper transactionDataMapper) {
        this.transactionDataMapper = transactionDataMapper;
    }

    public CircleOperationModel transform(CircleOperation operation) {
        CircleOperationModel operationModel = null;
        if (operation != null) {
            operationModel = new CircleOperationModel(operation.getId());
            operationModel.setTransactionEntity(transactionDataMapper.transform(operation.getTransactionEntity()));
        }
        return operationModel;
    }

    public List<CircleOperationModel> transform(List<CircleOperation> operationList) {
        List<CircleOperationModel> modelsList = null;
        if (operationList != null && !operationList.isEmpty()) {
            modelsList = new ArrayList<>();
            for (CircleOperation co : operationList) {
                CircleOperationModel model = transform(co);
                modelsList.add(model);
            }
        }
        return modelsList;
    }

}
