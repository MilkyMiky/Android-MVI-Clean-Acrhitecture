package com.example.testproject.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.testproject.common.BaseFragment
import com.example.testproject.common.BaseView
import com.example.testproject.R
import com.example.testproject.databinding.FragmentTestBinding
import com.example.testproject.ui.test.TestIntent.GetSampleData
import io.reactivex.Observable
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestFragment : BaseFragment<FragmentTestBinding>(), BaseView<TestState> {

  private val vmTestScreen: TestViewModel by viewModel()

   override fun resLayoutId(): Int = R.layout.fragment_test

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
        Observable.just(GetSampleData)
      )
    ).subscribe(vmTestScreen.viewIntentsConsumer())
  }

  override fun handleStates() {
    vmTestScreen.stateReceived().observe(this, Observer { state -> render(state) })
  }

  override fun render(state: TestState) {
    viewBinding!!.viewState = state
  }
}