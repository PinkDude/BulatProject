package resources.Entities;

import resources.Abstractions.IReader;
import resources.Abstractions.IWriter;

import java.util.Arrays;
import java.util.Comparator;

public class Library {
    private int number;

    private final int defautBookCount = 10;
    private Book[] books = new Book[defautBookCount];
    private int bookCount = 0;
    private IWriter writer;

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public Book[] getBooks() {
        return books;
    }

    public void ChangeWriter(IWriter _writer){
        writer = _writer;
        for(int i = 0; i < bookCount; i++){
            books[i].ChangeWriter(writer);
        }
    }

    public Library(){

    }

    public Library(IWriter _writer){
        writer = _writer;
    }

    public Library(IWriter _writer, int _number){
        writer = _writer;
        number = _number;
    }

    public int getNumber(){
        return number;
    }

    public void setNumber(int _number){
        number = _number;
    }

    public int getBookCount(){
        return bookCount;
    }

    public void Write(){
        writer.WriteLine("Библиотека №" + number + ":");
        if(bookCount <= 0){
            writer.Write("\t");
            writer.WriteLine("Книг пока что нет");
            return;
        }
        for(int i = 0; i < bookCount; i++){
            writer.Write("\t");
            books[i].Write();
        }
    }

    public boolean AddBook(Book book){
        if(bookCount >= books.length){
            return false;
        }

        books[bookCount++] = book;

        if(bookCount > 1)
            Arrays.sort(books, 0, bookCount);

        return true;
    }

    public Book RemoveBook(String bookName){
        if(bookCount == 0)
            return null;

        Book oldBook = null;
        for(int i = 0; i < bookCount; i++){
            if(books[i].getName().equals(bookName)){
                oldBook = books[i];
                LeftGo(i);
                break;
            }
        }
        return oldBook;
    }

    private void LeftGo(int index){
        for(int i = index; i < bookCount; i++){
            books[i] = books[i + 1];
        }
        books[--bookCount] = null;
    }

    public static Library CreateLibrary(IWriter writer, IReader reader){
        writer.WriteLine("Какой номер библиотеки?");
        var libraryNumber = reader.ReadInt();
        return new Library(writer, libraryNumber);
    }
}
