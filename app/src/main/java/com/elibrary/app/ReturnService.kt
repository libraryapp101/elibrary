package com.elibrary.app

import com.google.firebase.firestore.FirebaseFirestore

class ReturnService {
    private val db = FirebaseFirestore.getInstance()

    fun returnBook(issueId: String) {
        val updates = mapOf(
            "status" to "RETURNED",
            "returnDate" to System.currentTimeMillis()
        )
        db.collection("issues").document(issueId).update(updates)
            .addOnSuccessListener { println("Issue $issueId marked RETURNED") }
            .addOnFailureListener { e -> println("Error: ${e.message}") }
    }
}

