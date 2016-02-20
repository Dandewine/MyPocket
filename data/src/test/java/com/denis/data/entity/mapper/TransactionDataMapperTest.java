package com.denis.data.entity.mapper;

import org.junit.Test;


public class TransactionDataMapperTest{

    /*private TransactionDataMapper dataMapper;
    private WalletDataMapper mapper = new WalletDataMapper();

    private static final int FAKE_ID = 92;
    private static final float FAKE_AMOUNT = 100;
    private static final int FAKE_TYPE = 0;
    private static final long FAKE_DATE = 14784934923L;
    private static final int FAKE_CATEGORY_ID = 17;

    private static final int FAKE_WALLET_ID = 921;
    private static final String FAKE_WALLET_NAME = "fake_wallet";
    private static final String FAKE_WALLET_CURRENCY = "fake_$";
    private static final float FAKE_WALLET_BALANCE = 100;


    @Before
    public void setUp() {
        dataMapper = new TransactionDataMapper(mapper);
    }

    @Test
    public void testTransactionToTransactionEntity() {
        Transaction transaction = createFakeTransaction();
        TransactionEntity entity = dataMapper.transform(transaction);

        assertThat(entity,is(notNullValue()));
        assertThat(entity.getId(),is(FAKE_ID));
        assertThat(entity.getAmount(),is(FAKE_AMOUNT));
        assertThat(entity.getCategoryId(),is(FAKE_CATEGORY_ID));
        assertThat(entity.getType(),is(FAKE_TYPE));
        assertThat(entity.getUnixDateTime(),is(FAKE_DATE));

        assertThat(entity.getWalletEntity(),is(notNullValue()));
        assertThat(entity.getWalletEntity().getId(),is(FAKE_WALLET_ID));
        assertThat(entity.getWalletEntity().getName(),is(FAKE_WALLET_NAME));
        assertThat(entity.getWalletEntity().getCurrency(),is(FAKE_WALLET_CURRENCY));
        assertThat(entity.getWalletEntity().getBalance(),is(FAKE_WALLET_BALANCE));
    }

    @Test
    public void testTransactionEntityToTransaction() {
        TransactionEntity entity= createFakeTransactionEntity();
        Transaction transaction = dataMapper.transform(entity);

        assertThat(transaction,is(notNullValue()));
        assertThat(transaction.getId(),is(FAKE_ID));
        assertThat(transaction.getAmount(),is(FAKE_AMOUNT));
        assertThat(transaction.getCategoryId(),is(FAKE_CATEGORY_ID));
        assertThat(transaction.getType(),is(FAKE_TYPE));
        assertThat(transaction.getUnixDateTime(),is(FAKE_DATE));

        assertThat(transaction.getWallet(),is(notNullValue()));
        assertThat(transaction.getWallet().getId(),is(FAKE_WALLET_ID));
        assertThat(transaction.getWallet().getName(),is(FAKE_WALLET_NAME));
        assertThat(transaction.getWallet().getCurrency(),is(FAKE_WALLET_CURRENCY));
        assertThat(transaction.getWallet().getBalance(),is(FAKE_WALLET_BALANCE));
    }

    @Test
    public void testListTransactionToListTransactionEntity() {
        Transaction transaction = mock(Transaction.class);
        Transaction transaction1 = mock(Transaction.class);

        List<Transaction> list = new ArrayList<>(2);
        list.add(transaction);
        list.add(transaction1);

        List<TransactionEntity> entities = dataMapper.transform(list);

        assertThat(entities, is(notNullValue()));
        assertThat(entities.toArray()[0], is(instanceOf(TransactionEntity.class)));
        assertThat(entities.toArray()[1], is(instanceOf(TransactionEntity.class)));
        assertThat(entities.size(), is(2));
    }

    @Test
    public void testListTransactionEntityToListTransaction() {
        TransactionEntity entity = mock(TransactionEntity.class);
        TransactionEntity entity1 = mock(TransactionEntity.class);

        List<TransactionEntity> list = new ArrayList<>(2);
        list.add(entity);
        list.add(entity1);

        List<Transaction> entities = dataMapper.transform(list);

        assertThat(entities, is(notNullValue()));
        assertThat(entities.toArray()[0], is(instanceOf(Transaction.class)));
        assertThat(entities.toArray()[1], is(instanceOf(Transaction.class)));
        assertThat(entities.size(), is(2));
    }

    private Transaction createFakeTransaction() {
        return new Transaction(FAKE_ID,createFakeWallet(),FAKE_AMOUNT,FAKE_TYPE,FAKE_DATE,FAKE_CATEGORY_ID);
    }

    private TransactionEntity createFakeTransactionEntity(){
        return new TransactionEntity(FAKE_ID,createFakeWalletEntity(),FAKE_AMOUNT,FAKE_TYPE,FAKE_CATEGORY_ID,FAKE_DATE);
    }

    private WalletEntity createFakeWalletEntity(){
        return new WalletEntity(FAKE_WALLET_ID,FAKE_WALLET_NAME,FAKE_WALLET_CURRENCY,FAKE_WALLET_BALANCE);
    }
    private Wallet createFakeWallet(){
        return new Wallet(FAKE_WALLET_ID,FAKE_WALLET_NAME,FAKE_WALLET_CURRENCY,FAKE_WALLET_BALANCE);
    }*/
}
