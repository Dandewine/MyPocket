package com.denis.data.entity.mapper;

import com.denis.data.ApplicationTestCase;
import com.denis.data.entity.ExpenseCategoryEntity;
import com.denis.domain.models.ExpenseCategory;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class ExpenseCategoryDataMapperTest extends ApplicationTestCase {

    private final static int FAKE_ID = 12;
    private final static String FAKE_NAME = "girls";
    private final static String FAKE_PATH = "path";

    private ExpenseCategoryDataMapper dataMapper;

    @Before
    public void setUp() {
        dataMapper = new ExpenseCategoryDataMapper();
    }

    @Test
    public void testExpenseCategoryToExpenseCategoryEntity() {
        ExpenseCategory category = createFakeExpenseCategory();
        ExpenseCategoryEntity entity = dataMapper.transform(category);

        assertThat(entity, is(notNullValue()));
        assertThat(entity.getId(), is(FAKE_ID));
        assertThat(entity.getPath(), is(FAKE_PATH));
        assertThat(entity.getName(), is(FAKE_NAME));
    }

    @Test
    public void testExpenseCategoryEntityToExpenseCategory() {
        ExpenseCategoryEntity entity = createFakeExpenseCategoryEntity();
        ExpenseCategory category = dataMapper.transform(entity);

        assertThat(category, is(notNullValue()));
        assertThat(category.getId(), is(FAKE_ID));
        assertThat(category.getPath(), is(FAKE_PATH));
        assertThat(category.getName(), is(FAKE_NAME));
    }

    @Test
    public void testListExpenseCategoryToListExpenseCategoryEntities() {
        ExpenseCategory expenseCategory = mock(ExpenseCategory.class);
        ExpenseCategory expenseCategory1 = mock(ExpenseCategory.class);

        List<ExpenseCategory> list = new ArrayList<>(5);
        list.add(expenseCategory);
        list.add(expenseCategory1);

        List<ExpenseCategoryEntity> entities = dataMapper.transform(list);

        assertThat(entities.toArray()[0], is(instanceOf(ExpenseCategoryEntity.class)));
        assertThat(entities.toArray()[1], is(instanceOf(ExpenseCategoryEntity.class)));
        assertThat(entities, is(notNullValue()));
        assertThat(entities.size(), is(2));
    }

    @Test
    public void testListExpenseCategoryEntitiesToListExpenseCategories() {
        ExpenseCategoryEntity expenseCategory = mock(ExpenseCategoryEntity.class);
        ExpenseCategoryEntity expenseCategory1 = mock(ExpenseCategoryEntity.class);

        List<ExpenseCategoryEntity> list = new ArrayList<>(5);
        list.add(expenseCategory);
        list.add(expenseCategory1);

        List<ExpenseCategory> entities = dataMapper.transform(list);

        assertThat(entities.toArray()[0], is(instanceOf(ExpenseCategory.class)));
        assertThat(entities.toArray()[1], is(instanceOf(ExpenseCategory.class)));
        assertThat(entities, is(notNullValue()));
        assertThat(entities.size(), is(2));
    }

    private ExpenseCategory createFakeExpenseCategory() {
        return new ExpenseCategory(
                FAKE_ID,
                FAKE_NAME,
                FAKE_PATH
        );
    }

    private ExpenseCategoryEntity createFakeExpenseCategoryEntity() {
        return new ExpenseCategoryEntity(
                FAKE_ID,
                FAKE_NAME,
                FAKE_PATH
        );
    }

}
