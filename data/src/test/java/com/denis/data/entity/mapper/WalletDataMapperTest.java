package com.denis.data.entity.mapper;

import com.denis.data.ApplicationTestCase;

public class WalletDataMapperTest extends ApplicationTestCase {

   /* private static final int FAKE_WALLET_ID = 123;
    private static final String FAKE_WALLET_NAME = "TestWallet";
    private static final String FAKE_CURRENCY = "$";
    private static final float FAKE_BALANCE = 2000;

    private WalletDataMapper dataMapper;

    @Before
    public void setUp() {
        dataMapper = new WalletDataMapper();
    }

    @Test
    public void testWalletToWalletEntity() {
        Wallet wallet = createFakeWallet();
        WalletEntity walletEntity = dataMapper.transform(wallet);

        assertThat(walletEntity, is(notNullValue()));
        assertThat(walletEntity.getId(), is(FAKE_WALLET_ID));
        assertThat(walletEntity.getBalance(), is(FAKE_BALANCE));
        assertThat(walletEntity.getName(), is(FAKE_WALLET_NAME));
        assertThat(walletEntity.getCurrency(), is(FAKE_CURRENCY));
    }

    @Test
    public void testListWalletsToListWalletEntities() {
        Wallet wallet = mock(Wallet.class);
        Wallet wallet1 = mock(Wallet.class);

        List<Wallet> list = new ArrayList<>(5);
        list.add(wallet);
        list.add(wallet1);

        List<WalletEntity> entities = dataMapper.transform(list);

        assertThat(entities.toArray()[0], is(instanceOf(WalletEntity.class)));
        assertThat(entities.toArray()[1], is(instanceOf(WalletEntity.class)));
        assertThat(entities, is(notNullValue()));
        assertThat(entities.size(), is(2));
    }

    @Test
    public void testWalletEntityToWallet() {
        WalletEntity entity = createFakeWalletEntity();
        Wallet wallet = dataMapper.transform(entity);

        assertThat(wallet, is(notNullValue()));
        assertThat(wallet.getId(), is(FAKE_WALLET_ID));
        assertThat(wallet.getBalance(), is(FAKE_BALANCE));
        assertThat(wallet.getName(), is(FAKE_WALLET_NAME));
        assertThat(wallet.getCurrency(), is(FAKE_CURRENCY));
    }

    @Test
    public void testListWalletEntitiesToListWallets() {
        WalletEntity wallet = mock(WalletEntity.class);
        WalletEntity wallet1 = mock(WalletEntity.class);

        List<WalletEntity> list = new ArrayList<>(5);
        list.add(wallet);
        list.add(wallet1);

        List<Wallet> wallets = dataMapper.transform(list);

        assertThat(wallets.toArray()[0], is(instanceOf(Wallet.class)));
        assertThat(wallets.toArray()[1], is(instanceOf(Wallet.class)));
        assertThat(wallets, is(notNullValue()));
        assertThat(wallets.size(), is(2));
    }

    private WalletEntity createFakeWalletEntity() {
        return new WalletEntity(
                FAKE_WALLET_ID,
                FAKE_WALLET_NAME,
                FAKE_CURRENCY,
                FAKE_BALANCE
        );
    }

    private Wallet createFakeWallet() {
        return new Wallet(
                FAKE_WALLET_ID,
                FAKE_WALLET_NAME,
                FAKE_CURRENCY,
                FAKE_BALANCE
        );
    }
*/

}
