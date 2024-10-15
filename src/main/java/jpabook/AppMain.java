package jpabook;

import java.util.List;

public class AppMain {

    public static void main(String[] args) {
        test순수한객체_양방향();
    }

    private static void test순수한객체_양방향(){
        Team team1 = new Team("team1", "팀1");
        Member member1 = new Member("mebmer1", "회원1");
        Member member2 = new Member("mebmer2", "회원2");
        member1.setTeam(team1); // 연관관계 설정 member1 -> team1
        team1.getMembers().add(member1); // 연관관계 설정 team1-> member1

        member2.setTeam(team1); // 연관관계 설정 member2 -> team1
        team1.getMembers().add(member2); // 연관관계설정 team1 -> member2

        List<Member> members = team1.getMembers();
        System.out.println("members.size = " + members.size());


    }
}