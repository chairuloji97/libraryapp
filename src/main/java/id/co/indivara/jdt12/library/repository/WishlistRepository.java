package id.co.indivara.jdt12.library.repository;

import id.co.indivara.jdt12.library.model.Book;
import id.co.indivara.jdt12.library.model.Reader;
import id.co.indivara.jdt12.library.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    Optional<Wishlist> findByBookAndReader(Book book, Reader reader);
}
