package io.mobile.library_sys;


public interface LibraryManager {
    void registerMember(String id, String name);
    void removeMember(String id);

    void addBook(String id, String title);
    void removeBook(String id);

    void lendBook(String memberId, String bookId);
    void returnBook(String memberId, String bookId);

    void printOverdueBooks(String memberId);
    void printLendingHistory(String memberId);
}

