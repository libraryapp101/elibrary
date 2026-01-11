fun issueBook(bookId: String, admission: String) {
    val issue = hashMapOf(
        "bookId" to bookId,
        "admissionNumber" to admission,
        "issueDate" to System.currentTimeMillis(),
        "status" to "ISSUED"
    )

    FirebaseFirestore.getInstance()
        .collection("issues")
        .add(issue)
}
