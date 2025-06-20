package io.mobile.library_sys;

import java.util.*;

public class LibraryService implements LibraryManager, ReadingRoomManager {
    private final Map<String, Member> members = new HashMap<>();
    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, List<LendingRecord>> lendingRecords = new HashMap<>();
    private final Map<String, List<AccessLog>> accessLogs = new HashMap<>();

    @Override
    public void registerMember(String id, String name) {
        members.put(id, new Member(id, name));
    }

    @Override
    public void removeMember(String id) {
        members.remove(id);
    }

    @Override
    public void addBook(String id, String title) {
        books.put(id, new Book(id, title));
    }

    @Override
    public void removeBook(String id) {
        books.remove(id);
    }

    @Override
    public void lendBook(String memberId, String bookId) {
        Book book = books.get(bookId);
        if (book != null && !book.isLent()) {
            book.lend();
            lendingRecords.computeIfAbsent(memberId, k -> new ArrayList<>()).add(new LendingRecord(bookId));
        }
    }

    @Override
    public void returnBook(String memberId, String bookId) {
        Book book = books.get(bookId);
        if (book != null && book.isLent()) {
            book.returnBook();
            List<LendingRecord> records = lendingRecords.get(memberId);
            if (records != null) {
                for (LendingRecord record : records) {
                    if (record.getBookId().equals(bookId) && !record.isReturned()) {
                        record.returnBook();
                        break;
                    }
                }
            }
        }
    }

    public void BorrowBook(String bookId) {
        for (Map.Entry<String, List<LendingRecord>> entry : lendingRecords.entrySet()) {
            String memberId = entry.getKey();
            List<LendingRecord> records = entry.getValue();

            for (LendingRecord record : records) {
                if (record.getBookId().equals(bookId) && !record.isReturned()) {
                    System.out.println("이 책은 현재 " + memberId + " 회원이 대출 중입니다.");
                    return;
                }
            }
        }
        System.out.println("이 책은 도서관에 있습니다.");
    }



    @Override
    public void printOverdueBooks(String memberId) {
        List<LendingRecord> records = lendingRecords.get(memberId);
        if (records != null) {
            for (LendingRecord record : records) {
                if (record.isOverdue()) {
                    System.out.println(record);
                }
            }
        }
    }

    @Override
    public void printLendingHistory(String memberId) {
        List<LendingRecord> records = lendingRecords.get(memberId);
        if (records != null) {
            records.forEach(System.out::println);
        }
    }



//qr코드로 독서실 출입 관리
    private String extractMemberId(String qrCode) {
        return qrCode.replace("QR_", "");
    }

    @Override
    public void enterReadingRoom(String qrCode) {
        String memberId = extractMemberId(qrCode);
        accessLogs.computeIfAbsent(memberId, k -> new ArrayList<>()).add(new AccessLog());
    }

    @Override
    public void exitReadingRoom(String qrCode) {
        String memberId = extractMemberId(qrCode);
        List<AccessLog> logs = accessLogs.get(memberId);
        if (logs != null && !logs.isEmpty()) {
            for (int i = logs.size() - 1; i >= 0; i--) {
                if (logs.get(i).isPresent()) {
                    logs.get(i).exit();
                    break;
                }
            }
        }
    }

    @Override
    public void printCurrentOccupancy() {
        for (Map.Entry<String, List<AccessLog>> entry : accessLogs.entrySet()) {
            for (AccessLog log : entry.getValue()) {
                if (log.isPresent()) {
                    System.out.println("입실 중: " + entry.getKey());
                }
            }
        }
    }

    @Override
    public void printAccessHistory(String memberId) {
        List<AccessLog> logs = accessLogs.get(memberId);
        if (logs != null) {
            logs.forEach(System.out::println);
        }
    }
    public void printAllMembers() {
        if (members.isEmpty()) {
            System.out.println("등록된 회원이 없습니다.");
            return;
        }

        System.out.println("===== 전체 회원 목록 =====");
        for (Member member : members.values()) {
            System.out.println(member);  // toString() 자동 호출됨
        }
    }


}



