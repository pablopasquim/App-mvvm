package com.example.mvvm.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mvvm.model.Livro
import com.example.mvvm.model.Validacao

class LivroViewModel: ViewModel() {

    var listaLivros = mutableStateOf(listOf<Livro>())
        private set

    fun salvarLivro(titulo: String, autor: String) : String{

        if (Validacao.haCamposVazios(titulo, autor)){
            return "Preencha todos os campos"
        }

        val livro = Livro(
            Validacao.getId(),
            titulo,
            autor
        )

       listaLivros.value += livro

       return "Livro cadastrado!"
    }

    fun excluirLivros(id: Int){

        listaLivros.value = listaLivros.value.filter { it.id != id }
    }

    fun atualizarLitro(id: Int, titulo: String, autor: String) : String {

        if (Validacao.haCamposVazios(titulo,autor)){
            return "Preencha todos os campos"
    }

        var livro = Livro(id, titulo, autor)

        var tempLivro = listaLivros.value.filter { it.id == id }
        tempLivro
}