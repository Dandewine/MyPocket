package com.denis.mypocket.model.mapper;

import com.denis.domain.models.CycleOperation;
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

    public CycleOperationModel transform(CycleOperation operation) {
        CycleOperationModel operationModel = null;
        if (operation != null) {
            operationModel = new CycleOperationModel(operation.getId());
            operationModel.setTransactionEntity(transactionDataMapper.transform(operation.getTransaction()));
            operationModel.setName(operation.getName());
        }
        return operationModel;
    }

    public List<CycleOperationModel> transform(List<CycleOperation> operationList) {
        List<CycleOperationModel> modelsList = null;
        if (operationList != null && !operationList.isEmpty()) {
            modelsList = new ArrayList<>();
            for (CycleOperation co : operationList) {
                CycleOperationModel model = transform(co);
                modelsList.add(model);
            }
        }
        return modelsList;
    }

}
