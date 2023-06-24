package id.co.indivara.jdt12.library.repository;

import id.co.indivara.jdt12.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByOrderByStockAsc();
    List<Book> findAllByOrderByTitleAsc();
    List<Book> findAllByOrderByReadingCountAsc();
    List<Book> findAllByOrderByWaitingListAsc();

    Optional<Book> findById(Integer id);

    Optional<Book> findByTitle(String title);
}
