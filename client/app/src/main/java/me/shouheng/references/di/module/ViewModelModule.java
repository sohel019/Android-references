package me.shouheng.references.di.module;

import android.arch.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import me.shouheng.references.di.base.ViewModelKey;
import me.shouheng.references.viewmodel.MainViewModel;

/**
 * Created by WngShhng on 2018/6/7.*/
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);
}