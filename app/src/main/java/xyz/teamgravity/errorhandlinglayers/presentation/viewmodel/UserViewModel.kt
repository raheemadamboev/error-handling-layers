package xyz.teamgravity.errorhandlinglayers.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import xyz.teamgravity.errorhandlinglayers.R
import xyz.teamgravity.errorhandlinglayers.core.extension.asErrorText
import xyz.teamgravity.errorhandlinglayers.core.util.UniversalText
import xyz.teamgravity.errorhandlinglayers.domain.error.PasswordError
import xyz.teamgravity.errorhandlinglayers.domain.repository.AuthRepository
import xyz.teamgravity.errorhandlinglayers.domain.util.Result
import xyz.teamgravity.errorhandlinglayers.domain.util.UserDataValidator

class UserViewModel(
    private val validator: UserDataValidator,
    private val repository: AuthRepository
) : ViewModel() {

    private val _event = Channel<UserEvent>()
    val event: Flow<UserEvent> = _event.receiveAsFlow()

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun onRegister(password: String) {
        viewModelScope.launch {
            when (val validation = validator.validatePassword(password)) {
                is Result.Success -> {
                    when (val result = repository.register(password)) {
                        is Result.Success -> Unit
                        is Result.Error -> {
                            val message = result.asErrorText()
                            _event.send(UserEvent.Error(message))
                        }
                    }
                }

                is Result.Error -> {
                    val message = when (validation.error) {
                        PasswordError.TooShort -> UniversalText.Resource(R.string.error_password_too_short)
                        PasswordError.NoUppercase -> UniversalText.Dynamic("No uppercase")
                        PasswordError.NoDigit -> UniversalText.Dynamic("No digit")
                    }
                    _event.send(UserEvent.Error(message))
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // MISC
    ///////////////////////////////////////////////////////////////////////////

    sealed interface UserEvent {
        data class Error(val message: UniversalText) : UserEvent
    }
}