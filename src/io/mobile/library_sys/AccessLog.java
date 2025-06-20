package io.mobile.library_sys;

import java.time.LocalDateTime;

// 출입로그 관리
public class AccessLog {
    private final LocalDateTime enterTime; // 입실
    private LocalDateTime leaveTime; // 퇴실

    public AccessLog() {
        this.enterTime = LocalDateTime.now();
    }

    public void exit() {
        this.leaveTime = LocalDateTime.now();
    }

//퇴실시간이 안찍히면 퇴실
    public boolean isPresent() {
        return leaveTime == null;
    }

    @Override
    public String toString() {
        return "입실: " + enterTime + (leaveTime != null ? ", 퇴실: " + leaveTime : ", 퇴실 전");
    }
}
