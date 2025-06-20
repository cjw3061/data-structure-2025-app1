package io.mobile.library_sys;

public interface ReadingRoomManager {
    void enterReadingRoom(String qrCode);
    void exitReadingRoom(String qrCode);

    void printCurrentOccupancy();
    void printAccessHistory(String memberId);
}
