package com.bridgelabz.bookstorebackend.service.serviceInterface;

import com.bridgelabz.bookstorebackend.dto.BookDTO;
import com.bridgelabz.bookstorebackend.model.BookData;

import java.util.List;

public interface IBookService {
    BookData createBook(BookDTO bookDTO);

    List<BookData> getAllBookData();

    BookData getBookDataById(int bookId);

    void deleteRecordByBookId(int bookId);

    BookData updateRecordById(Integer bookId, BookDTO bookDTO);

    List<BookData> getBookByName(String bookName);

    List<BookData> getBookByNameAjax(String bookName);

    List<BookData> sortedBooksInAscendingOrder();

    List<BookData> sortedBooksInDescendingOrder();
}
