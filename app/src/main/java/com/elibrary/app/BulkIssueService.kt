package com.elibrary.app

import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class BulkIssueService {
    private val db = FirebaseFirestore.getInstance()

    fun bulkIssueBooks(bookId: String, admissionNumbers: List<String>) {
        admissionNumbers.forEach { admission ->
            val issueId = UUID.randomUUID().toString()
            val issue = hashMapOf(
                "issueId" to issueId,
                "bookId" to bookId,
                "admissionNumber" to admission,
                "issueDate" to System.currentTimeMillis(),
                "status" to "ISSUED"
            )
            db.collection("issues").document(issueId).set(issue)
                .addOnSuccessListener { println("Issued $bookId to $admission") }
                .addOnFailureListener { e -> println("Error: ${e.message}") }
        }
    }
}
