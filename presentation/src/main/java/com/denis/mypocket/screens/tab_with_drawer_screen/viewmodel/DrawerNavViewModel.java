package com.denis.mypocket.screens.tab_with_drawer_screen.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.SwitchCompat;
import android.view.SubMenu;
import android.view.View;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.facades.NavigationDrawerFacade;
import com.denis.domain.models.User;
import com.denis.domain.models.Wallet;
import com.denis.mypocket.R;
import com.denis.mypocket.databinding.NavDrawerHeaderBinding;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.model.UserModel;
import com.denis.mypocket.model.WalletModel;
import com.denis.mypocket.model.mapper.ModelMapper;
import com.denis.mypocket.model.mapper.UserModelMapper;
import com.denis.mypocket.model.mapper.WalletModelDataMapper;
import com.denis.mypocket.viewmodel.ViewModel;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import rx.Subscriber;

/**
 * Created by denis on 4/29/16.
 */
@PerActivity // TODO: 5/26/16 Multiple instance of this class, after logout
public class DrawerNavViewModel implements ViewModel {

    private NavigationDrawerFacade facadeInterceptor;
    private Context context;
    private ModelMapper<User, UserModel> userModelMapper = new UserModelMapper();
    private ModelMapper<Wallet, WalletModel> modelMapper = new WalletModelDataMapper();

    private UserModel userModel = null;
    private List<WalletModel> walletsList = new ArrayList<>();
    private SwitchCompat[] switchCompats;

    private Subscriber<List<User>> userSubscriber = new DefaultSubscriber<List<User>>() {
        @Override
        public void onNext(List<User> users) {
            if (users != null && !users.isEmpty())
                userModel = userModelMapper.toModel(users.get(0));
        }
    };

    @Inject
    public DrawerNavViewModel(Context context, NavigationDrawerFacade facadeInterceptor) {
        this.facadeInterceptor = facadeInterceptor;
        this.context = context;

        facadeInterceptor.retriveUser(userSubscriber); //retrieve user
        facadeInterceptor.retrieveAllWallets(new WalletsSubscriber()); //retrieve his wallets
    }

    @Override
    public void destroy() {
        userModelMapper = null;
        context = null;
        facadeInterceptor.unSubscribe();
        switchCompats = null;
    }

    public void logout() {
        facadeInterceptor.logout(new LogoutSubscriber());
    }

    public UserModel getUser() {
        return userModel;
    }

    public void updateWallets(List<WalletModel> wallets) {
        List<Wallet> walletList = modelMapper.fromModel(wallets);
        facadeInterceptor.updateWallets(new WalletsSubscriber(), walletList);
    }

    @RxLogSubscriber
    private class LogoutSubscriber extends DefaultSubscriber<Integer> {
        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(Integer code) {
            if (code == HttpsURLConnection.HTTP_OK)
                facadeInterceptor.deleteToken(new DeleteTokenSubscriber());

        }
    }

    @RxLogSubscriber
    private class DeleteTokenSubscriber extends DefaultSubscriber<Boolean> {
        @Override
        public void onNext(Boolean isTokenDeleted) {
            if (isTokenDeleted)
                facadeInterceptor.deleteUser(new DeleteUserSubscriber());
        }
    }

    @RxLogSubscriber
    private class DeleteUserSubscriber extends DefaultSubscriber<Boolean> {
        @Override
        public void onNext(Boolean isTokenDeleted) {
            if (isTokenDeleted) {
                String packageName = context.getPackageName();
                Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        }
    }

    @RxLogSubscriber
    private class WalletsSubscriber extends DefaultSubscriber<List<Wallet>> {
        @Override
        public void onNext(List<Wallet> wallets) {
            if (wallets != null && !wallets.isEmpty()) {
                walletsList.clear();
                walletsList.addAll(modelMapper.toModel(wallets));
            }
        }
    }

    public WalletModel getActiveWallet() {
        WalletModel walletModel = null;
        for (WalletModel walletModel1 : walletsList) {
            if (walletModel1.isActive()) {
                walletModel = walletModel1;
                break;
            }
        }
        return walletModel;
    }

    public ArrayList<WalletModel> getWalletsList() {
        return (ArrayList<WalletModel>) walletsList;
    }

    //// TODO: 6/4/16  On UI user can turn off all wallets, need to make active switch unclickable of uncheckable
    public void showWallets(View v, NavigationView navView, NavDrawerHeaderBinding binding) {
        navView.getMenu().clear();

        if (!v.isSelected()) {
            v.setSelected(true);
            navView.inflateMenu(R.menu.menu_drawer_wallets); //inflate secondary drawer

            if (switchCompats == null)
                switchCompats = new SwitchCompat[walletsList.size()]; //create array of switches to set state in onCheckedListener

            SubMenu subMenu = navView.getMenu().getItem(0).getSubMenu();

            for (int i = 0; i < 5; i++) { //user can have mas only 5 wallets, so nav drawer has only 5 items
                if (i < walletsList.size()) {//walletlist can be < 5
                    String walletName = walletsList.get(i).getName();
                    subMenu.getItem(i).setTitle(walletName); //set title to item

                    SwitchCompat actionView = (SwitchCompat) subMenu.getItem(i).getActionView();
                    actionView.setTag(i); //set index of walletList, this need to get wallet by id from OnCheckedListener
                    actionView.setChecked(walletsList.get(i).isActive());

                    //on checked listener
                    actionView.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        for (SwitchCompat switchCompat : switchCompats) {
                            if (switchCompat.isChecked())
                                switchCompat.setChecked(false); //turn off all switches
                        }

                        buttonView.setChecked(isChecked); //set checked to chosen switch

                        int tagIdx = (int) buttonView.getTag();
                        WalletModel model = walletsList.get(tagIdx); //get model by tag (id) that we putted one step before
                        model.setActive(isChecked);
                        if (isChecked) { //if user switch active wallet update db
                            updateWallets(walletsList);
                            binding.setWallet(getActiveWallet()); //bind model to drawer
                        }
                    });

                    switchCompats[i] = actionView;

                } else {
                    subMenu.getItem(i).setVisible(false);
                }
            }
        } else { //return primary drawer
            v.setSelected(false);
            navView.inflateMenu(R.menu.menu_drawer);
        }
    }
}
