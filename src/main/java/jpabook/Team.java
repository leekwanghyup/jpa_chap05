package jpabook;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString(exclude = "members")
public class Team {
    @Id
    @Column(name="TEAM_ID")
    private String id;
    private String name;

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // == 추가== /
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();
}
