package com.example.testproject.ui.demo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.testproject.common.BaseFragment
import com.example.testproject.common.BaseView
import com.example.testproject.R
import com.example.testproject.databinding.FragmentDemoBinding
import com.example.testproject.ui.demo.DemoIntent.*
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.Observable
import org.koin.androidx.viewmodel.ext.android.viewModel

class DemoFragment : BaseFragment<FragmentDemoBinding>(), BaseView<DemoState> {

  private val vmDemoScreen: DemoViewModel by viewModel()

   override fun resLayoutId(): Int = R.layout.fragment_demo

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    handleStates()
  }

 override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = super.onCreateView(inflater, container, savedInstanceState)
    .also {
      initIntents()
    }

  override fun initIntents() {
    viewSubscriptions = Observable.merge(
      listOf(
        Observable.just(GetSampleData),

          viewBinding!!.btnPush.clicks()
              .map { ShowMessageIntent },

          viewBinding!!.btnDummy.clicks()
              .map { DummyIntent }
      )
    ).subscribe(vmDemoScreen.viewIntentsConsumer())
  }

  override fun handleStates() {
    vmDemoScreen.stateReceived().observe(this, Observer {
            state -> render(state) }
    )
  }

  override fun render(state: DemoState) {
    viewBinding!!.viewState = state
    Log.d("log","State $state")
      if (state.showMessage) {
          Snackbar.make(viewBinding!!.root, "This is message", Snackbar.LENGTH_SHORT).show()
      }

  }
}