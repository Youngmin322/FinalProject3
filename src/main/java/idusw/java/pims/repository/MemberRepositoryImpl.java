package idusw.java.pims.repository;

import idusw.java.pims.model.Member;

import java.util.List;

public class MemberRepositoryImpl implements MemberRepository {
    @Override
    public int create(Member member) {
        return 0;
    }

    @Override
    public Member readByEmail(Member member) {
        return null;
    }

    @Override
    public List<Member> readList() {
        return null;
    }

    @Override
    public int update(Member member) {
        return 0;
    }

    @Override
    public int delete(Member member) {
        return 0;
    }

    @Override
    public List<Member> findMembersOrderByEmail(String order) {
        return null;
    }

    @Override
    public List<Member> getMemberList() {
        return null;
    }

    @Override
    public void setMemberList(List<Member> memberList) {

    }

    @Override
    public void readFile() {

    }

    @Override
    public void saveFile() {

    }

    @Override
    public void applyUpdate() {

    }
}
