package com.adielgarcia.gympro.presentation.mainlayout

data class ChangePasswordUiState(
    val oldPassword: String = "",
    val newPassword: String = "",
    val confirmPassword: String = ""
)
