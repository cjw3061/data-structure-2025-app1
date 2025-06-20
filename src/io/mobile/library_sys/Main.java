package io.mobile.library_sys;


public class Main {
    public static void main(String[] args) {
        LibraryService system = new LibraryService();

        // 1. 도서 등록
        system.addBook("B001", "자바의 정석");
        system.addBook("B002", "자료구조와 알고리즘");

        // 1.1 도서 삭제
        system.removeBook("B002");

        // 2. 회원 등록
        system.registerMember("20250101", "홍길동");
        system.registerMember("20250102", "조우진");

        //2.2 회원 삭제
        system.removeMember("20250102");

        // 3. 도서 대여
        system.lendBook("20250101", "B001");

        // 4. 대여 내역 출력
        System.out.println("=== 대여 내역 ===");
        system.printLendingHistory("20250101");

        //도서 확인
        system.BorrowBook("B001");

        // 5. 도서 반납
        system.returnBook("20250101", "B001");

        //도서 확인
        system.BorrowBook("B001");


        // 6. 연체 여부 확인
        System.out.println("=== 연체 내역 ===");
        system.printOverdueBooks("20250101");

        // 7. 독서실 입실
        system.enterReadingRoom("QR_20250101");

        // 8. 현재 입실 중인 인원 확인
        System.out.println("=== 현재 입실 중인 회원 ===");
        system.printCurrentOccupancy();

        // 9. 독서실 퇴실
        system.exitReadingRoom("QR_20250101");

        // 10. 입퇴실 기록 확인
        System.out.println("=== 홍길동 입퇴실 기록 ===");
        system.printAccessHistory("20250101");
    }
}