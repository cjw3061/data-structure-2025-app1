package io.mobile.library_sys;

import java.time.LocalDate;


public class LendingRecord {
    private final String bookId; // 대여한 도서 ID
    private final LocalDate lendDate; // 대여일
    private final LocalDate dueDate; // 반납 마감일
    private LocalDate returnDate; // 반납일

    public LendingRecord(String bookId) {
        this.bookId = bookId;
        this.lendDate = LocalDate.now();
        this.dueDate = lendDate.plusDays(14); // 14일 후 마감
    }

    public void returnBook() {
        this.returnDate = LocalDate.now(); // 반납 처리
    }

    public boolean isOverdue() {
        return returnDate == null && LocalDate.now().isAfter(dueDate);
    }

    public boolean isReturned(){
        return returnDate != null;
    }

    public String getBookId() {
        return bookId;
    }

    @Override
    public String toString() {
        return "도서ID: " + bookId + ", 대여일: " + lendDate + ", 반납기한: " + dueDate +
                (returnDate != null ? ", 반납일: " + returnDate : ", 미반납");
    }
}
