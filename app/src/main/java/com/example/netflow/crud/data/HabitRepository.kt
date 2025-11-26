package com.example.netflow.crud.data

import com.example.netflow.auth.data.models.Habit
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.CollectionReference

class HabitRepository {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private fun habitsCollection(): CollectionReference {
        val user = auth.currentUser

        return if (user != null) {
            db.collection("users")
                .document(user.uid)
                .collection("habits")
        } else {
            db.collection("no_user_habits_placeholder")
        }
    }

    fun addHabit(habit: Habit, onResult: (Boolean) -> Unit) {
        val col = habitsCollection()

        val id = col.document().id
        col.document(id)
            .set(habit.copy(id = id))
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    fun deleteHabit(id: String, onResult: (Boolean) -> Unit) {
        habitsCollection().document(id)
            .delete()
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    fun updateHabit(habit: Habit, onResult: (Boolean) -> Unit) {
        habitsCollection().document(habit.id)
            .set(habit)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    fun getHabit(onChange: (List<Habit>) -> Unit) {
        habitsCollection()
            .addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    val habits = snapshot.toObjects(Habit::class.java)
                    onChange(habits)
                }
            }
    }
}