package   com.adielgarcia.gympro.presentation.mainlayout

sealed interface ChangePasswordEvents {
    data class ChangeOldPassword(val oldPassword: String) : ChangePasswordEvents
    data class ChangeNewPassword(val newPassword: String) : ChangePasswordEvents
    data class ChangeConfirmPassword(val confirmPassword: String) : ChangePasswordEvents

    data class OnChangePassword(val id: Int, val onLogOut: () -> Unit) : ChangePasswordEvents
    data object Reset : ChangePasswordEvents
}