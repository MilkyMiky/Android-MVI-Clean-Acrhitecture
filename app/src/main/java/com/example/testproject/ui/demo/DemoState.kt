package com.example.testproject.ui.demo

data class DemoState(
  val showMessage: Boolean = false,
  val success: Boolean = false,
  val loading: Boolean = false,
  val error: Throwable? = null
)

sealed class DemoIntent {
  object GetSampleData : DemoIntent()
  object ShowMessageIntent : DemoIntent()
  object DummyIntent : DemoIntent()
}

sealed class DemoStateChange {
  class Error(val error: Throwable) : DemoStateChange()
  object HideError : DemoStateChange()
  object Loading : DemoStateChange()
  object ShowMessage : DemoStateChange()
  object DontShowMessage : DemoStateChange()
  object Success : DemoStateChange()
}