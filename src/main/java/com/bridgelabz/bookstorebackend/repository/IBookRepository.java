package com.bridgelabz.bookstorebackend.repository;

import com.bridgelabz.bookstorebackend.model.BookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IBookRepository extends JpaRepository<BookData, Integer> {

    public  BookData findByBookId(int bookId);

    List<BookData> findByBookName(String bookName);

    List<BookData>  findByBookNameContainingOrAuthorNameContaining(String bookName, String authorName);

    @Query(value = "select * from book_data order by price", nativeQuery = true)
    List<BookData> getSortedListOfBooksInAsc();

    @Query(value = "select * from book_data order by price desc", nativeQuery = true)
    List<BookData> getSortedListOfBooksInDesc();
}
