package id.co.indivara.jdt12.library.service;

import id.co.indivara.jdt12.library.Exception.ResponseEntityErrorException;
import id.co.indivara.jdt12.library.model.Book;
import id.co.indivara.jdt12.library.model.BorrowRecord;
import id.co.indivara.jdt12.library.model.Reader;
import id.co.indivara.jdt12.library.repository.BorrowRecordRepository;
import id.co.indivara.jdt12.library.util.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {

    @Autowired
    BorrowRecordRepository borrowRecordRepository;

    @Override
    public BorrowRecord findByBookAndReader(Book book, Reader reader) {
        Optional<BorrowRecord> borrowRecordOptional = borrowRecordRepository.findByBookAndReader(book,reader);
        if(!borrowRecordOptional.isPresent()){
            throw new ResponseEntityErrorException("Record not found", HttpStatus.NOT_FOUND);
        };
        return borrowRecordOptional.get();
    }

    @Override
    public BorrowRecord createBorrowRecord(Book book, Reader reader) {
        BorrowRecord newBorrowRecord = new BorrowRecord();
        newBorrowRecord.setBorrowDate(new Date());
        newBorrowRecord.setBook(book);
        newBorrowRecord.setReader(reader);
        newBorrowRecord.setPenalty(0);

        LocalDate date = LocalDate.now().plusDays(7);
        Date returnDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        newBorrowRecord.setDueDate(returnDate);

        return borrowRecordRepository.save(newBorrowRecord);
    }

    @Override
    public BorrowRecord updateRecordForReturnedBook(Book book, Reader reader){
        Optional<BorrowRecord> borrowRecordOptional = borrowRecordRepository.findByBookAndReaderAndReturnDate(book, reader, null);
        if(!borrowRecordOptional.isPresent()){
            throw new ResponseEntityErrorException("Record not found",HttpStatus.NOT_FOUND);
        };
        BorrowRecord borrowRecord = borrowRecordOptional.get();

        //CEK DAN UPDATE RECORD DENDA
        Integer penalty = GeneralUtil.calculatePenalty(borrowRecord.getDueDate());
        borrowRecord.setReturned(true);
        borrowRecord.setPenalty(penalty);
        borrowRecord.setReturnDate(new Date());
        return borrowRecordRepository.save(borrowRecord);
    }

    public boolean alreadyBorrowed(Book book, Reader reader) {
        Optional<BorrowRecord> borrowRecordOptional = borrowRecordRepository.findByBookAndReaderAndReturnDate(book,reader, null);

        if(borrowRecordOptional.isPresent()){
            BorrowRecord record = borrowRecordOptional.get();

            if(record.getReturnDate() == null){
                return true;
            }
        };
        return false;
    }
}
