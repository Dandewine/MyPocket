package com.denis.mypocket.model.mapper;

import com.denis.domain.models.CircleOperation;
import com.denis.mypocket.model.CycleOperationModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CycleOperationModelMapper {

    private TransactionModelDataMapper transactionDataMapper;

    @Inject
    public CycleOperationModelMapper(TransactionModelDataMapper transactionDataMapper) {
        this.transactionDataMapper = transactionDataMapper;
    }

    public CycleOperationModel transform(CircleOperation operation) {
        CycleOperationModel operationModel = null;
        if (operation != null) {
            operationModel = new CycleOperationModel(operation.getId());
            operationModel.setTransactionEntity(transactionDataMapper.transform(operation.getTransactionEntity()));
        }
        return operationModel;
    }

    public List<CycleOperationModel> transform(List<CircleOperation> operationList) {
        List<CycleOperationModel> modelsList = null;
        if (operationList != null && !operationList.isEmpty()) {
            modelsList = new ArrayList<>();
            for (CircleOperation co : operationList) {
                CycleOperationModel model = transform(co);
                modelsList.add(model);
            }
        }
        return modelsList;
    }

}
