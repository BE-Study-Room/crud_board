package study.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.crud.dto.BoardUpdateForm;
import study.crud.entity.Board;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BoardRepository extends JpaRepository<Board, UUID> {
    Optional<Board> findByTitle(String title);

}
