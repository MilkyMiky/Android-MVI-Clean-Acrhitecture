package com.example.testproject.ui.test

import com.example.testproject.common.BaseViewModel
import com.example.testproject.common.startWithAndErrHandleWithIO
import com.example.testproject.ui.test.TestStateChange.*
import com.example.testproject.ui.test.TestIntent.*
import io.reactivex.Observable

class TestViewModel() : BaseViewModel<TestState>() {

  override fun initState(): TestState = TestState()

  override fun viewIntents(intentStream: Observable<*>): Observable<Any> = 
    Observable.merge(
      listOf(
           intentStream.ofType(GetSampleData::class.java)
                    .map { Success }
                    .startWithAndErrHandleWithIO(Loading) { Observable.just(Error(it), HideError) }
       )
    )

  override fun reduceState(previousState: TestState, stateChange: Any): TestState = 
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

            else -> previousState
        }
}