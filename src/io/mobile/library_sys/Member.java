package io.mobile.library_sys;

public class Member {
    private final String id;
    private final String name;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId(){
        return id;
}
    public String getName() {
        return name;
    }

@Override
public String toString() {
    return "회원ID: " + id + ", 이름: " + name;
    }
}
