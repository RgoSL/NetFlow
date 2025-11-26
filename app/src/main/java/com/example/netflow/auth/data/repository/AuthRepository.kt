package com.example.netflow.auth.data.repository

import com.google.firebase.auth.FirebaseAuth

/* Classe da Definição de Métodos Para a Funcionalidade de Autenticação */

class AuthRepository {

    private  val auth = FirebaseAuth.getInstance() /* Variável Privada que Instância os Métodos do FirebaseAuth */

    /* Criação de um Método Para o Login Utilizando um Objeto do Firebase */
    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) { /* Método Recebe Email e Senha Como Parâmetros */
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                /* Condicionais Para Confirmar os Dados de Login */
                if (it.isSuccessful) onResult(true, null)
                else onResult(false, it.exception?.message)
            }
    }

    /* Criação de um Método Para o Cadastro de Usuários Utilizando um Objeto do Firebase */
    fun register(email: String, password: String, onResult: (Boolean, String?) -> Unit) { /* Método Recebe Email e Senha Como Parâmetros */
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                /* Condicionais Para Salvar os Dados do Registro */
                if (it.isSuccessful) onResult(true, null)
                else onResult(false, it.exception?.message)
            }
    }
    /* Criação de um Método Para Sair da Conta Atual Utilizando um Objeto do Firebase */
    fun logout() {
        auth.signOut()
    }

    /* Criação de um Método Para Incrementar um ID de Usuário Utilizando um Objeto do Firebase */
    fun currentUserId(): String? = auth.currentUser?.uid
}

    fun createAccount(
    email: String,
    password: String,
    onResult: (Boolean, String?) -> Unit
    ) {
    FirebaseAuth.getInstance()
        .createUserWithEmailAndPassword(email, password)
        .addOnSuccessListener { onResult(true, null) }
        .addOnFailureListener { e -> onResult(false, e.message) }
    }
