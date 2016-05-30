package com.denis.mypocket.model.mapper;

import com.denis.domain.models.CycleOperation;
import com.denis.mypocket.model.CycleOperationModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CycleOperationModelMapper implements ModelMapper<CycleOperation,CycleOperationModel>{

    private TransactionModelDataMapper transactionDataMapper;

    @Inject
    public CycleOperationModelMapper(TransactionModelDataMapper transactionDataMapper) {
        this.transactionDataMapper = transactionDataMapper;
    }

    public CycleOperationModel toModel(CycleOperation operation) {
        CycleOperationModel operationModel = null;
        if (operation != null) {
            operationModel = new CycleOperationModel(operation.getId());
            operationModel.setTransactionModel(transactionDataMapper.toModel(operation.getTransaction()));
            operationModel.setName(operation.getName());
        }
        return operationModel;
    }

    @Override
    public List<CycleOperationModel> toModel(List<CycleOperation> operationList) {
        List<CycleOperationModel> modelsList = null;
        if (operationList != null && !operationList.isEmpty()) {
            modelsList = new ArrayList<>();
            for (CycleOperation co : operationList) {
                CycleOperationModel model = toModel(co);
                modelsList.add(model);
            }
        }
        return modelsList;
    }

    @Override
    public List<CycleOperation> fromModel(List<CycleOperationModel> cycleOperationModels) {
        return null;
    }

    @Override
    public CycleOperation fromModel(CycleOperationModel cycleOperationModel) {
        return null;
    }
}
