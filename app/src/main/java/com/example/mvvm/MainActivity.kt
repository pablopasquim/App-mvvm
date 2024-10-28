package com.example.mvvm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvvm.viewmodel.LivroViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainLayout()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(){

    var titulo by remember {
        mutableStateOf("")
    }

    var autor by remember {
        mutableStateOf("")
    }

    var modoEditar by remember {
        mutableStateOf(false)
    }

    var textoBtn by remember {
        mutableStateOf("Cadastrar")
    }

    var idLivro by remember {
        mutableStateOf(0)
    }

    var context = LocalContext.current

    var focusManager = LocalFocusManager.current

    var livroViewModel : LivroViewModel = viewModel()

    var listaLivros by livroViewModel.listaLivros

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(text = "Cadastrar Livro",
            fontSize = 20.sp, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(10.dp))

        TextField(value = titulo,
            onValueChange = { titulo = it },
            label = { Text(text = "Titulo") })

        Spacer(modifier = Modifier.height(10.dp))

        TextField(value = titulo,
            onValueChange = { titulo = it },
            label = { Text(text = "Titulo") })

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {

            var retorno = livroViewModel.salvarLivro(titulo, autor)

            Toast.makeText(
                context,
                retorno,
                Toast.LENGTH_SHORT
            ).show()

            titulo = ""
            autor = ""
            focusManager.clearFocus()
        },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = textoBtn)
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Livros Cadastrados", fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth())


        LazyColumn{

            items(listaLivros){

                livro ->

                Text(text = "${livro.titulo} (${livro.autor}")

                Row {
                    Button(onClick = {
                        livroViewModel.excluirLivros(livro.id)
                    }) {
                        Text(text = "Excluir")
                    }

                    Button(onClick = {

                        modoEditar = true
                        titulo = livro.titulo
                        autor = livro.autor
                        idLivro = livro.id

                    }) {
                        Text(text = "Editar")
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

    }
}
