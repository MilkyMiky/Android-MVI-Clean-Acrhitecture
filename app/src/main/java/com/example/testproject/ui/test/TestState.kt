package com.example.testproject.ui.test

data class TestState(
  val success: Boolean = false,  val loading: Boolean = false,
  val error: Throwable? = null
)

sealed class TestIntent {
  object GetSampleData : TestIntent()
}

sealed class TestStateChange {
  class Error(val error: Throwable) : TestStateChange()
  object HideError : TestStateChange()
  object Loading : TestStateChange()
  object Success : TestStateChange()
}