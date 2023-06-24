package id.co.indivara.jdt12.library.repository;

import id.co.indivara.jdt12.library.model.Book;
import id.co.indivara.jdt12.library.model.BorrowRecord;
import id.co.indivara.jdt12.library.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Integer> {

    Optional<BorrowRecord> findByBookAndReaderAndReturnDate(Book book, Reader reader, Date returnDate);

    Optional<BorrowRecord> findByBookAndReader(Book book, Reader reader);
}
