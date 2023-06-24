package id.co.indivara.jdt12.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer bookId;
    private String title;
    private String author;
    private Integer stock;
    private boolean available;
    private Integer readingCount;
    private Integer waitingList;
    private Integer pages;
}
