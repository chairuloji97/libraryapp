package id.co.indivara.jdt12.library.controller;

import id.co.indivara.jdt12.library.handler.Response;
import id.co.indivara.jdt12.library.model.BorrowRecord;
import id.co.indivara.jdt12.library.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    //Pembaca dapat meminjam buku
    @PostMapping("/borrow/{bookId}/{readerId}")
    public ResponseEntity<Object> borrowBook(@PathVariable("bookId") Integer bookId, @PathVariable("readerId") Integer readerId) throws Exception {
        BorrowRecord borrowRecord = transactionService.borrowBook(bookId, readerId);
        Response response = new Response(HttpStatus.CREATED, borrowRecord);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Mengembalikan Buku
    @PutMapping("/return-book/{bookId}/{readerId}")
    public ResponseEntity<Object> returnBook(@PathVariable("bookId") Integer bookId, @PathVariable("readerId") Integer readerId) throws Exception{
        BorrowRecord returnRecord = transactionService.returnBook(bookId,readerId);
        Response response = new Response(HttpStatus.OK, returnRecord);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
