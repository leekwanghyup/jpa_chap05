package jpabook;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {
    public static void main( String[] args ) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            //testSave(em);
            //queryLogicJoin(em);
            //updateRelation(em);
            //deleteRelation(em);
            //deleteEntity(em);
            biDirection(em);
            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }

    public static void testSave(EntityManager em){
        Team team1 = new Team("team1","팀1");
        em.persist(team1);

        // 회원1 저장
        Member member1 = new Member("member1","회원1");
        member1.setTeam(team1);
        em.persist(member1);

        // 회원2 저장
        Member member2 = new Member("member2","회원2");
        member2.setTeam(team1);
        em.persist(member2);
    }

    public static void queryLogicJoin(EntityManager em){
        String jpql = "select m from Member m join m.team t where t.name=:teamName";

        List<Member> resultList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "팀1")
                .getResultList();

        resultList.forEach(m->{
            System.out.println("[query] member.username="+m.getUsername());
        });
    }

    public static void updateRelation(EntityManager em){
        Team team2 = new Team("team2","팀2");
        em.persist(team2);

        // 회원1에 새로운 2팀 설정
        Member member = em.find(Member.class, "member1");
        member.setTeam(team2);
    }

    public static void deleteRelation(EntityManager em){
        Member member = em.find(Member.class, "member1");
        member.setTeam(null); // 연관관계 제거
    }

    public static void deleteEntity(EntityManager em){
        Member member = em.find(Member.class, "member2");
        member.setTeam(null); // 회원2 연관관계 제거
        Team team1 = em.find(Team.class, "team1");
        Team team2 = em.find(Team.class, "team2");
        em.remove(team1);
        em.remove(team2);
    }

    public static void biDirection(EntityManager em){
        Team team = em.find(Team.class, "team1");
        List<Member> members = team.getMembers(); // 객체 그래프 탐색 : 팀->회원
        members.forEach(m->{
            System.out.println("member.username="+m.getUsername());
        });
    }
}
