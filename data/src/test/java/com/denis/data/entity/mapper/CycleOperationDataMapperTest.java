package com.denis.data.entity.mapper;

import com.denis.data.ApplicationTestCase;
import com.denis.data.entity.CycleOperationEntity;
import com.denis.data.entity.TransactionEntity;
import com.denis.domain.models.CycleOperation;
import com.denis.domain.models.Transaction;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class CycleOperationDataMapperTest extends ApplicationTestCase{
    private CycleOperationDataMapper dataMapper;

    private static final int FAKE_ID = 34;
    private static final String FAKE_NAME = "gas";
    private static final String FAKE_INTERVAL = CycleOperation.CircleTypes.YEAR.getValue();

    @Mock private TransactionEntity entity;
    @Mock private Transaction transaction;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private TransactionDataMapper transactionDataMapper;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        dataMapper = new CycleOperationDataMapper(transactionDataMapper);
    }

    @Test
    public void testCycleOperationToCycleOperationEntity(){
        CycleOperation operation = createCycleOperation();
        CycleOperationEntity entity = dataMapper.transform(operation);

        assertThat(entity, is(notNullValue()));
        assertThat(entity.getId(),is(FAKE_ID));
        assertThat(entity.getInterval(),is(FAKE_INTERVAL));
        assertThat(entity.getName(),is(FAKE_NAME));
        assertThat(entity.getTransactionEntity(),is(notNullValue()));
        assertThat(entity.getTransactionEntity(),is(instanceOf(TransactionEntity.class)));
    }

    @Test
    public void testCycleOperationEntityToCycleOperation(){
        CycleOperationEntity entity = createCycleOperationEntity();
        CycleOperation operation = dataMapper.transform(entity);

        assertThat(operation, is(notNullValue()));
        assertThat(operation.getId(),is(FAKE_ID));
        assertThat(operation.getInterval(),is(FAKE_INTERVAL));
        assertThat(operation.getName(),is(FAKE_NAME));
        assertThat(operation.getTransaction(),is(notNullValue()));
        assertThat(operation.getTransaction(),is(instanceOf(Transaction.class)));
    }

    @Test
    public void testCycleOperationListToCycleOperationEntityList(){
        CycleOperation operation = mock(CycleOperation.class);
        CycleOperation operation1 = mock(CycleOperation.class);

        List<CycleOperation> list = new ArrayList<>(5);
        list.add(operation);
        list.add(operation1);

        List<CycleOperationEntity> entities = dataMapper.transform(list);

        assertThat(entities, is(notNullValue()));
        assertThat(entities.size(), is(2));

        assertThat(entities.get(0), is(instanceOf(CycleOperationEntity.class)));
        assertThat(entities.get(1), is(instanceOf(CycleOperationEntity.class)));

        assertThat(entities.get(0).getTransactionEntity(),is(notNullValue()));
        assertThat(entities.get(1).getTransactionEntity(),is(notNullValue()));


    }

    @Test
    public void testCycleOperationEntityListToCycleOperationList(){
        CycleOperationEntity entity = mock(CycleOperationEntity.class);
        CycleOperationEntity entity1 = mock(CycleOperationEntity.class);

        List<CycleOperationEntity> list = new ArrayList<>(5);
        list.add(entity);
        list.add(entity1);

        List<CycleOperation> entities = dataMapper.transform(list);

        assertThat(entities, is(notNullValue()));
        assertThat(entities.size(), is(2));

        assertThat(entities.get(0), is(instanceOf(CycleOperation.class)));
        assertThat(entities.get(1), is(instanceOf(CycleOperation.class)));

        assertThat(entities.get(0), is(notNullValue()));
        assertThat(entities.get(1), is(notNullValue()));

        assertThat(entities.get(0).getTransaction(),is(notNullValue()));
        assertThat(entities.get(1).getTransaction(),is(notNullValue()));
    }

    private CycleOperationEntity createCycleOperationEntity(){
        return new CycleOperationEntity(FAKE_ID,FAKE_NAME,entity,FAKE_INTERVAL);
    }

    private CycleOperation createCycleOperation(){
        return new CycleOperation(FAKE_ID,transaction,FAKE_NAME,FAKE_INTERVAL);
    }
}
