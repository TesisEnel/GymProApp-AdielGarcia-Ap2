package  com.adielgarcia.gympro.presentation.mainlayout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adielgarcia.gympro.data.remote.dto.utilities.edit.ChangePasswordDto
import com.adielgarcia.gympro.data.remote.repository.UsuariosRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val userRepos: UsuariosRepos
) : ViewModel() {
    private val _uiState = MutableStateFlow(ChangePasswordUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: ChangePasswordEvents) {

        when (event) {
            is ChangePasswordEvents.ChangeOldPassword -> {
                _uiState.value = _uiState.value.copy(oldPassword = event.oldPassword)
            }

            is ChangePasswordEvents.ChangeNewPassword -> {
                _uiState.value = _uiState.value.copy(newPassword = event.newPassword)
            }

            is ChangePasswordEvents.ChangeConfirmPassword -> {
                _uiState.value = _uiState.value.copy(confirmPassword = event.confirmPassword)
            }

            is ChangePasswordEvents.Reset -> {
                _uiState.value = ChangePasswordUiState()
            }

            is ChangePasswordEvents.OnChangePassword -> {
                viewModelScope.launch {
                    userRepos.changePassword(
                        ChangePasswordDto(
                            event.id,
                            _uiState.value.newPassword,
                        )
                    ).collect {
                        event.onLogOut()
                    }
                }
            }
        }
    }


}