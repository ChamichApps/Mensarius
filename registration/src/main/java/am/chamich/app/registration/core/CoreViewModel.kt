package am.chamich.app.registration.core

import am.chamich.app.registration.exceptions.Failure
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class CoreViewModel : ViewModel() {

    protected val failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}
