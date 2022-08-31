package com.bridgelabz.bookstorebackend.controller;

import com.bridgelabz.bookstorebackend.dto.BookDTO;
import com.bridgelabz.bookstorebackend.dto.ResponseDTO;
import com.bridgelabz.bookstorebackend.model.BookData;
import com.bridgelabz.bookstorebackend.service.serviceInterface.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    IBookService bookService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> addBookToRepository(@Valid @RequestBody BookDTO bookDTO) {
        BookData newBook = bookService.createBook(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("New Book Added in Book Store", newBook);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllBookData() {
        List<BookData> listOfBooks = bookService.getAllBookData();
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully :", listOfBooks);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/getBy/{bookId}")
    public ResponseEntity<ResponseDTO> getBookDataById(@PathVariable int bookId) {
        BookData book = bookService.getBookDataById(bookId);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully by id :", book);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{bookId}")
    public String deleteRecordById(@PathVariable int bookId) {
        bookService.deleteRecordByBookId(bookId);
        return "Book Deleted.";
    }

    @PutMapping("/updateBookById/{bookId}")
    public ResponseEntity<ResponseDTO> updateRecordById(@PathVariable Integer bookId, @Valid @RequestBody BookDTO bookDTO) {
        BookData updateRecord = bookService.updateRecordById(bookId, bookDTO);
        ResponseDTO dto = new ResponseDTO(" Book Record updated successfully by Id", updateRecord);
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "searchByBookName/{bookName}")
    public ResponseEntity<ResponseDTO> getBookByName(@PathVariable String bookName) {
        List<BookData> fetchedRecord = bookService.getBookByName(bookName);
        ResponseDTO dto = new ResponseDTO(" Book retrieve successfully by Name", fetchedRecord);
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping(value="searchByBookNameAjax/{bookName}")
    public ResponseEntity<ResponseDTO> getBookByNameAjax(@PathVariable String bookName){
        List<BookData> fetchedRecord = bookService.getBookByNameAjax(bookName);
        ResponseDTO dto = new ResponseDTO(" Book retrieve successfully by Name", fetchedRecord);
        System.out.println("All books" + fetchedRecord);
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/sortBookByAsc")
    public ResponseEntity<ResponseDTO> getBooksInAscendingOrder() {
        List<BookData> bookList = bookService.sortedBooksInAscendingOrder();
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully :", bookList);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/sortBookByDesc")
    public ResponseEntity<ResponseDTO> getBooksInDescendingOrder() {
        List<BookData> listOfBooks = bookService.sortedBooksInDescendingOrder();
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully :", listOfBooks);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
