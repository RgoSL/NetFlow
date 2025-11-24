package com.example.netflow.auth.data.models

/* Classe da Modelagem da Entidade Hábitos */

/* Propriedades da Entidade */
data class Habit(
    val id: String = "", /* Chave-Primária */
    val title: String = "", /* Título */
    val description: String = "", /* Descrição */
    val date: Long = System.currentTimeMillis(), /* Data de Criação */
    val completed: Boolean = false, /* Status */
    val userId: String = "" /* Responsável Pelo Hábito */
)