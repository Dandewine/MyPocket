package com.denis.data.entity.mapper;

import com.denis.data.ApplicationTestCase;

public class IncomeCategoryDataMapperTest extends ApplicationTestCase {

   /* private IncomeCategoryDataMapper dataMapper;
    private static final int FAKE_ID = 92;
    private static final String FAKE_NAME = "find";
    private static final String FAKE_PATH = "fake_path";


    @Before
    public void setUp() {
        dataMapper = new IncomeCategoryDataMapper();
    }

    @Test
    public void testIncomeCategoryToIncomeCategoryEntity() {
        IncomeCategory category = createFakeIncomeCategory();
        IncomeCategoryEntity entity = dataMapper.transform(category);

        assertThat(entity, is(notNullValue()));
        assertThat(entity.getId(), is(FAKE_ID));
        assertThat(entity.getPath(), is(FAKE_PATH));
        assertThat(entity.getName(), is(FAKE_NAME));
    }

    @Test
    public void testIncomeCategoryEntityToIncomeCategory() {
        IncomeCategoryEntity entity = createFakeIncomeCategoryEntity();
        IncomeCategory category = dataMapper.transform(entity);

        assertThat(category, is(notNullValue()));
        assertThat(category.getId(), is(FAKE_ID));
        assertThat(category.getPath(), is(FAKE_PATH));
        assertThat(category.getName(), is(FAKE_NAME));
    }

    @Test
    public void testListIncomeCategoryToListIncomeCategoryEntities() {
        IncomeCategory IncomeCategory = mock(IncomeCategory.class);
        IncomeCategory IncomeCategory1 = mock(IncomeCategory.class);

        List<IncomeCategory> list = new ArrayList<>(5);
        list.add(IncomeCategory);
        list.add(IncomeCategory1);

        List<IncomeCategoryEntity> entities = dataMapper.transform(list);

        assertThat(entities.toArray()[0], is(instanceOf(IncomeCategoryEntity.class)));
        assertThat(entities.toArray()[1], is(instanceOf(IncomeCategoryEntity.class)));
        assertThat(entities, is(notNullValue()));
        assertThat(entities.size(), is(2));
    }

    @Test
    public void testListIncomeCategoryEntitiesToListIncomeCategories() {
        IncomeCategoryEntity IncomeCategory = mock(IncomeCategoryEntity.class);
        IncomeCategoryEntity IncomeCategory1 = mock(IncomeCategoryEntity.class);

        List<IncomeCategoryEntity> list = new ArrayList<>(5);
        list.add(IncomeCategory);
        list.add(IncomeCategory1);

        List<IncomeCategory> entities = dataMapper.transform(list);

        assertThat(entities.toArray()[0], is(instanceOf(IncomeCategory.class)));
        assertThat(entities.toArray()[1], is(instanceOf(IncomeCategory.class)));
        assertThat(entities, is(notNullValue()));
        assertThat(entities.size(), is(2));
    }

    private IncomeCategory createFakeIncomeCategory() {
        return new IncomeCategory(FAKE_ID, FAKE_NAME, FAKE_PATH);
    }

    private IncomeCategoryEntity createFakeIncomeCategoryEntity() {
        return new IncomeCategoryEntity(FAKE_ID, FAKE_NAME, FAKE_PATH);
    }*/
}
