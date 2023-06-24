package id.co.indivara.jdt12.library.controller;

import id.co.indivara.jdt12.library.handler.Response;
import id.co.indivara.jdt12.library.model.Book;
import id.co.indivara.jdt12.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class WishlistController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<Object> addBooks(@RequestBody Book book){
        Book addBook = bookService.addBook(book);
        Response response = new Response(HttpStatus.CREATED, addBook);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/find-book/{id}")
    public ResponseEntity<Object> findBookById(@PathVariable("id") Integer id){
        Book book = bookService.findById(id);
        Response response = new Response(HttpStatus.OK, book);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateBook(@RequestBody Book book, @PathVariable("id") Integer id){
        Book updateBook = bookService.updateBook(book, id);
        Response response = new Response(HttpStatus.CREATED, updateBook);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") Integer id){
        bookService.deleteById(id);
        Boolean success = true;
        Response response = new Response(HttpStatus.CREATED, true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
