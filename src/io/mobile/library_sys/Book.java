package io.mobile.library_sys;

/// 책 추가 및 제거
public class Book {
    private final String id; // 책 id
    private final String title; // 책 제목
    private boolean isLent = false; // 대여 확인

    public Book(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public boolean isLent() {
        return isLent;
    }

    public void lend() {
        isLent = true;
    } // 대여
    public void returnBook() {
        isLent = false;
    } // 반납

    @Override
    public String toString() {
        return "도서ID: " + id + ", 제목: " + title + ", 대여중: " + isLent;
    }
}
