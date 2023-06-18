package idusw.java.pims.view;

import idusw.java.pims.PimsApplication;
import idusw.java.pims.model.Member;

import java.util.*;

public class NavView {
    private Scanner sc = new Scanner(System.in);

    public int showMenu(boolean isLogin, boolean isRoot) {
        Member m = null;
        if (!isLogin) { // 로그인 전
            System.out.println("0.종료, 1.로그인, 3.등록");
        } else { // 로그인 후
            if (isRoot) // 관리자 : root학번@induk.ac.kr 관리자 계정, 실행시점에 등록되도록 함.
                System.out.println("0.종료, 2.로그아웃, 4.조회, 5.수정, 8.이름으로 정렬, 9.이메일검색");
            else
                System.out.println("0.종료, 2.로그아웃, 4.조회, 5.수정, 6.탈퇴, 7.이메일&이름 목록");
        }
        int menu;
        try {
            menu = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("숫자만 입력해주세요.");
            menu = -1;
        }
        switch (menu) {
            case 0:
                System.exit(0);
                break;
            case 1:
                m = new RequestView().login();
                if (m == null) {
                    System.out.println("Illegal request");
                    break;
                }
                PimsApplication.memberController.requestLogin(m);
                break;
            case 2:
                m = PimsApplication.session.get("login");
                if (m == null) {
                    System.out.println("Illegal request");
                    break;
                }
                PimsApplication.memberController.requestLogout(m);
                break;
            case 3:
                m = new RequestView().register();
                if (m == null) {
                    System.out.println("Illegal request");
                    break;
                }
                PimsApplication.memberController.requestRegister(m);
                break;
            case 4: {
                if (isLogin) {
                    Member loggedInMember = PimsApplication.session.get("login");
                    if (loggedInMember == null) {
                        System.out.println("Illegal request");
                        break;
                    }
                    PimsApplication.memberController.requestViewProfile(loggedInMember);
                } else {
                    System.out.println("Please log in to view the profile.");
                }
                break;
            }

            case 5: {
                if (isLogin) {
                    Member loggedInMember = PimsApplication.session.get("login");
                    if (loggedInMember == null) {
                        System.out.println("Illegal request");
                        break;
                    }
                    PimsApplication.memberController.requestUpdateProfile(loggedInMember);
                } else {
                    System.out.println("Please log in to update the profile.");
                }
                break;
            }

            case 6: {
                if (isLogin) {
                    Member loggedInMember = PimsApplication.session.get("login");
                    if (loggedInMember == null) {
                        System.out.println("Illegal request");
                        break;
                    }
                    PimsApplication.memberController.requestDeleteAccount(loggedInMember);
                } else {
                    System.out.println("Please log in to delete the account.");
                }
                break;
            }

            case 7: {
                if (isLogin) {
                    Member loggedInMember = PimsApplication.session.get("login");
                    PimsApplication.memberController.requestPrintEmailNameList(loggedInMember);
                }
                break;
            }

            case 8: {
                if (isLogin && isRoot) {
                    Member loggedInMember = PimsApplication.session.get("login");
                    System.out.println(loggedInMember);
                    if (loggedInMember == null) {
                        System.out.println("Illegal request");
                        break;
                    }
                    List<Member> members = PimsApplication.memberController.getMembers();

                    System.out.println("Sort by name (1. 오름차순 정렬 / 2. 내림차순 정렬): ");
                    int sortOption = Integer.parseInt(sc.nextLine());

                    if (sortOption == 1) {
                        Collections.sort(members, Comparator.comparing(Member::getName));
                    } else if (sortOption == 2) {
                        Collections.sort(members, Comparator.comparing(Member::getName).reversed());
                    } else {
                        System.out.println("Invalid sort option");
                        break;
                    }

                    for (Member member : members) {
                        System.out.println(member.getName() + " | " + member.getEmail());
                    }
                } else {
                    System.out.println("Invalid request");
                }
                break;
            }

            case 9: {
                if (isLogin && isRoot) {
                    System.out.println("Enter email part to search: ");
                    String searchEmailPart = sc.nextLine();
                    List<Member> members = PimsApplication.memberController.getMembers();
                    List<Member> matchingMembers = new ArrayList<>();

                    for (Member member : members) {
                        if (member.getEmail().contains(searchEmailPart)) {
                            matchingMembers.add(member);
                        }
                    }

                    if (matchingMembers.isEmpty()) {
                        System.out.println("No members found with the provided email part.");
                    } else {
                        for (Member member : matchingMembers) {
                            System.out.println(member.getName() + " | " + member.getEmail());
                        }
                    }
                } else {
                    System.out.println("Invalid request");
                }
                break;
            }

            default:
                System.out.println("Confirm your selection");
                break;
        }

        return menu;
    }
}