package com.denis.mypocket.viewmodel.adding;

import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.CycleOperation;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.utils.PLTags;
import com.denis.mypocket.viewmodel.ViewModel;

@PerActivity
public class AddCycleOperationViewModel implements ViewModel {
    private UseCase<CycleOperation> addUseCase;
    public String name = "";

    public AddCycleOperationViewModel(UseCase<CycleOperation> addUseCase) {
        this.addUseCase = addUseCase;
    }

    public View.OnClickListener addOnClick = v -> addUseCase.executeSync(new AddCycleOperation(), new CycleOperation(null, name, CycleOperation.CircleTypes.WEEK.getValue()));

    @Override
    public void destroy() {

    }


    public void afterTextChanged(Editable s) {
        if (!TextUtils.equals(s.toString(), name))
            name = s.toString();
    }

    class AddCycleOperation extends DefaultSubscriber<CycleOperation> {
        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(CycleOperation operation) {
            Log.d(PLTags.CYCLE_OPERATIONS_TAG, "cycle operation was added \n " + operation.toString());
        }
    }
}
