package id.co.indivara.jdt12.library.controller;

import id.co.indivara.jdt12.library.handler.Response;
import id.co.indivara.jdt12.library.model.Book;
import id.co.indivara.jdt12.library.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/display-books")
    public ResponseEntity<Object> displayBooks(@RequestParam("orderBy") Optional<String> orderBy){
        List<Book> books = reportService.displayBooks(orderBy.orElse(null));
        Response response = new Response(HttpStatus.OK, books);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
