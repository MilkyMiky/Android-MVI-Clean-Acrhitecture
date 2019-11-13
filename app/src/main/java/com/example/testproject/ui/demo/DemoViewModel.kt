package com.example.testproject.ui.demo

import com.example.testproject.common.BaseViewModel
import com.example.testproject.common.startWithAndErrHandleWithIO
import com.example.testproject.ui.demo.DemoStateChange.*
import com.example.testproject.ui.demo.DemoIntent.*
import io.reactivex.Observable

class DemoViewModel : BaseViewModel<DemoState>() {

  override fun initState(): DemoState = DemoState()

  override fun viewIntents(intentStream: Observable<*>): Observable<Any> = 
    Observable.merge(
      listOf(
           intentStream.ofType(GetSampleData::class.java)
               .map { Success }
               .startWithAndErrHandleWithIO(Loading) { Observable.just(Error(it), HideError) },
//
          intentStream.ofType(ShowMessageIntent::class.java)
              .flatMap { Observable.just(ShowMessage, DontShowMessage) }
              .startWithAndErrHandleWithIO(Loading) { Observable.just(Error(it), HideError) },
//
          intentStream.ofType(DummyIntent::class.java)
              .map { true }
              .startWithAndErrHandleWithIO(Loading) { Observable.just(Error(it), HideError) }
       )
    )

  override fun reduceState(previousState: DemoState, stateChange: Any): DemoState = 
    when (stateChange) {
            is Loading -> previousState.copy(
                loading = true,
                success = false,
                error = null
            )

            is Success -> previousState.copy(
                loading = false,
                success = true,
                error = null
            )

            is Error -> previousState.copy(
                loading = false,
                success = false,
                error = stateChange.error
            )

            is HideError -> previousState.copy(error = null)

            is ShowMessage -> previousState.copy(showMessage = true)
//
            is DontShowMessage -> previousState.copy(showMessage = false)

            else -> previousState
        }
}