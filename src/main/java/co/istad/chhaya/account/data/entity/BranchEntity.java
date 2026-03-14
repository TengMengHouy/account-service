package co.istad.chhaya.account.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "branches")
@Getter
@Setter
@NoArgsConstructor
public class BranchEntity {

    @Id
    private UUID branchId;
    private String name;
    private Boolean isOpening;

}
