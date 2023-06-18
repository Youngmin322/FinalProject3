package idusw.java.pims.view;

import idusw.java.pims.model.Member;

/**
 * 응답을 처리하여 사용자에게 보여줌
 */
public class ResponseView {
    //회원 등록 성공 메시지 출력
    public void registerSuccess(Member m) {
        System.out.println("[Successful Registration] : " + m.getName());
    }

    //로그인 성공 메시지 출력
    public void loginSuccess(Member m) {
        System.out.println(" : " + m.getName());
    }

    //로그아웃 성공 메시지 출력
    public void logoutSuccess(Member m) {
        System.out.println("See you again :  " + m.getName());
    }
}
