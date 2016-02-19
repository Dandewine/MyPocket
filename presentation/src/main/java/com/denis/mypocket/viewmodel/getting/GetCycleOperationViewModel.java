package com.denis.mypocket.viewmodel.getting;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.CycleOperation;
import com.denis.mypocket.internal.di.PerFragment;
import com.denis.mypocket.model.CycleOperationModel;
import com.denis.mypocket.model.mapper.CycleOperationModelMapper;
import com.denis.mypocket.view.adapter.CycleOperationAdapter;
import com.denis.mypocket.viewmodel.ViewModel;

import java.util.List;

@PerFragment
public class GetCycleOperationViewModel implements ViewModel {
    public CycleOperationAdapter operationAdapter;

    private UseCase<CycleOperation> getUseCase;
    private CycleOperationModelMapper mapper;

    public GetCycleOperationViewModel(UseCase<CycleOperation> getUseCase, CycleOperationModelMapper modelMapper) {
        this.getUseCase = getUseCase;
        this.mapper = modelMapper;
        operationAdapter = new CycleOperationAdapter();

        getUseCase.executeSync(new CycleOperationSubscriber());
    }

    @Override
    public void destroy() {
        mapper = null;
        operationAdapter = null;
        getUseCase.unSubscribe();
    }

    private class CycleOperationSubscriber extends DefaultSubscriber<List<CycleOperation>>{
        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<CycleOperation> cycleOperations) {
            if(cycleOperations != null && !cycleOperations.isEmpty()) {
                List<CycleOperationModel> operationModels = mapper.transform(cycleOperations);
                operationAdapter.addAll(operationModels);
            }
        }
    }
}
