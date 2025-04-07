package com.adielgarcia.gympro.utils.extensors

fun String.maskCedula(): String {
    if (this.length != 11) return "Formato inválido"

    return "${this.substring(0, 3)}-${this.substring(3, 10)}-${this.substring(10)}"
}