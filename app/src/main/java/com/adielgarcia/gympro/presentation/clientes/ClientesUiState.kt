package  com.adielgarcia.gympro.presentation.clientes

import com.adielgarcia.gympro.data.remote.dto.utilities.fetching.GetUsuarioDto

data class ClientesUiState(
    val allClientes: List<GetUsuarioDto> = emptyList(),
    val clientes: List<GetUsuarioDto> = emptyList(),
    val search: String = ""
)
