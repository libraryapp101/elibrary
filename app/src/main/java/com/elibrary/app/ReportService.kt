package com.elibrary.app

import com.google.firebase.firestore.FirebaseFirestore
import java.io.File

class ReportService {
    private val db = FirebaseFirestore.getInstance()

    fun generateIssuedReport(fileName: String = "issued_books.csv") {
        db.collection("issues").get()
            .addOnSuccessListener { snapshot ->
                val file = File(fileName)
                file.printWriter().use { out ->
                    out.println("AdmissionNumber,BookID,Status,IssueDate,ReturnDate")
                    for (doc in snapshot.documents) {
                        val admission = doc.getString("admissionNumber") ?: ""
                        val bookId = doc.getString("bookId") ?: ""
                        val status = doc.getString("status") ?: ""
                        val issueDate = doc.getLong("issueDate") ?: 0
                        val returnDate = doc.getLong("returnDate") ?: 0
                        out.println("$admission,$bookId,$status,$issueDate,$returnDate")
                    }
                }
                println("Report saved to $fileName")
            }
            .addOnFailureListener { e -> println("Error: ${e.message}") }
    }
}
