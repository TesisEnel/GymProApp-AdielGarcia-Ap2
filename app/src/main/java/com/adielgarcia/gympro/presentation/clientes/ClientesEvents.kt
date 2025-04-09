package  com.adielgarcia.gympro.presentation.clientes

sealed interface ClientesEvents {
    data class OnSearchChange(val search: String) : ClientesEvents
    data class FindByEntrenador(val entrenadorId: Int) : ClientesEvents
}