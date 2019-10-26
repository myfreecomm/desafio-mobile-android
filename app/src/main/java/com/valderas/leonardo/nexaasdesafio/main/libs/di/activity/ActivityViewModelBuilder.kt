package com.valderas.leonardo.nexaasdesafio.main.libs.di.activity

import android.arch.lifecycle.ViewModel
import com.valderas.leonardo.nexaasdesafio.main.libs.di.viewmodel.ViewModelKey
import com.valderas.leonardo.nexaasdesafio.main.mvvm.view.main.MainActivity
import com.valderas.leonardo.nexaasdesafio.main.mvvm.view.pull.PullRequestActivity
import com.valderas.leonardo.nexaasdesafio.main.mvvm.viewmodel.MainViewModel
import com.valderas.leonardo.nexaasdesafio.main.mvvm.viewmodel.PullRequestViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ActivityViewModelBuilder {

    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @ContributesAndroidInjector
    internal abstract fun pullRequestActivity(): PullRequestActivity

    @Binds
    @IntoMap
    @ViewModelKey(PullRequestViewModel::class)
    abstract fun bindPullRequestViewModel(pullViewModel: PullRequestViewModel): ViewModel
}
