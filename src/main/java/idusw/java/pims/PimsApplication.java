package idusw.java.pims;

import idusw.java.pims.controller.MemberController;
import idusw.java.pims.model.Member;
import idusw.java.pims.repository.MemberRepository;
import idusw.java.pims.repository.MemberRepositoryImpl;
import idusw.java.pims.service.MemberService;
import idusw.java.pims.service.MemberServiceImpl;
import idusw.java.pims.view.NavView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PIMS(Personal Information Management System)<br>
 * 개인 정보를 관리할 수 있는 프로그램<br>
 */
public class PimsApplication {
    public static MemberController memberController;
    public static MemberService memberService;
    public static MemberRepository memberRepository;

    public static Map<String, Member> session;
    public static List<Member> memberPersistence; // File or

    static void lookup() {
        memberPersistence = new ArrayList<>();
        session = new HashMap<>();
        memberRepository = new MemberRepositoryImpl();
        memberService = new MemberServiceImpl();
        memberController = new MemberController();
    }

    public static void main(String[] args) {

        PimsApplication.lookup(); // 의존(dependency) 관계가 있는 객체를 주입합

        // 관리자 계정 포함 10개의 계정을 자동 등록하는 기능을 추가하시오
       initializeMembers();

        NavView navView = new NavView();

        boolean isLogin = false, isRoot = false;

        int menu;
        do {
            Member m = session.get("login");
            if (m != null) {
                isLogin = true;
                isRoot = m.getEmail().equals("root202212018@induk.ac.kr");
            }
            else {
                isLogin = false;
                isRoot = false;
            }
            menu = navView.showMenu(isLogin, isRoot);
        } while (menu != 0); // 종료 메뉴외에는 종료
    }

    public static void initializeMembers() {
        String[] memberList = {
                "root202212018@induk.ac.kr, pw, root, 01096599798, Seoul",
                "User1@induk.ac.kr, pw1, user1, 01061861861, Seoul",
                "User2@induk.ac.kr, pw2, user2, 01056418965, Busan",
                "User3@induk.ac.kr, pw3, user3, 01085489645, Daegu",
                "User4@induk.ac.kr, pw4, user4, 01048964694, Gwangju",
                "User5@induk.ac.kr, pw5, user5, 01049864664, Incheon",
                "User6@induk.ac.kr, pw6, user6, 01048964165, Gangwon",
                "User7@induk.ac.kr, pw7, user7, 01078942313, Gyeonggi",
                "User8@induk.ac.kr, pw8, user8, 01089756421, Ulsan",
                "User9@induk.ac.kr, pw9, user9, 01041856945, Jeju"
        };
        for(String m : memberList) {
            String[] input = m.split("\\s*,\\s*");
            Member member = new Member();
            member.setEmail(input[0]);
            member.setPw(input[1]);
            member.setName(input[2]);
            member.setPhone(input[3]);
            member.setAddress(input[4]);
            memberController.requestRegister(member);
            System.out.println("회원 등록 완료: " + member.getName() + "(" + member.getEmail() + ")");
        }
    }
}

