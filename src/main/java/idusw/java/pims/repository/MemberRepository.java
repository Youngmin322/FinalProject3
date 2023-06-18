package idusw.java.pims.repository;

import idusw.java.pims.model.Member;

import java.util.List;

public interface MemberRepository {
    int create(Member member);
    Member readByEmail(Member member);
    List<Member> readList();
    int update(Member member);
    int delete(Member member);

    List<Member> findMembersOrderByEmail(String order); // 기본 오름 차순

    List<Member> getMemberList();
    void setMemberList(List<Member> memberList);

    void readFile();
    void saveFile();
    void applyUpdate();
}
