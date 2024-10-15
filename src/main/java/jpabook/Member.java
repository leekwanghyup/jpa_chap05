package jpabook;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString(exclude = "team")
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    // 연관관계 매핑
    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team; // 팀의 참조 보관

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public void changeTeam(Team team) {
        // 기존 팀과 관계를 제거
        if(this.team!=null){
            this.team.getMembers().remove(this);
        }
        this.team = team;
        team.getMembers().add(this);
    }
}
