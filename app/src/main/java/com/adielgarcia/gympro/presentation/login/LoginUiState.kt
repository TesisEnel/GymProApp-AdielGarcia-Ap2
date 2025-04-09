package  com.adielgarcia.gympro.presentation.login

data class LoginUiState(
    val username: String = "",
    val usernameError: String? = null,

    val password: String = "",
    val passwordError: String? = null,

    val adminMode: Boolean = false,

    val errorMessage: String? = null,
    val isLoading: Boolean = false,

    val clickCount: Int = 0
)
