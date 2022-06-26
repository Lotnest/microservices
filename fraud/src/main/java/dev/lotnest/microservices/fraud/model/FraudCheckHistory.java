package dev.lotnest.microservices.fraud.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FraudCheckHistory {

    @Id
    @SequenceGenerator(name = "fraud_id_generator", sequenceName = "fraud_id_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fraud_id_generator")
    private Long id;
    private Long customerId;
    private Boolean isFraudster;
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FraudCheckHistory that = (FraudCheckHistory) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
