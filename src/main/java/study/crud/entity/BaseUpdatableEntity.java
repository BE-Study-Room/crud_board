package study.crud.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class BaseUpdatableEntity extends BaseEntity {
    @LastModifiedDate
    private Instant updatedAt;

    public void changeUpdatedAt() {
        this.updatedAt = Instant.now();
    }
}
