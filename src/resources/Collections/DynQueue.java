package resources.Collections;

import resources.Abstractions.IWriter;
import resources.Entities.Book;
import resources.Entities.Library;

public class DynQueue {
    private final int defaultCount = 10;
    private int count = 0;
    private Library[] array;
    private IWriter writer;

    public int getCount() {
        return count;
    }

    public Library[] getArray() {
        return array;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setArray(Library[] array) {
        this.array = array;
    }

    public void ChangeWriter(IWriter _writer){
        writer = _writer;
        for (int i = 0; i < count; i++){
            array[i].ChangeWriter(writer);
        }
    }

    public DynQueue(){
        array = new Library[defaultCount];
    }

    public DynQueue(IWriter _writer){
        array = new Library[defaultCount];
        writer = _writer;
    }

    public DynQueue(IWriter _writer, int count){
        array = new Library[count];
        writer = _writer;
    }

    public boolean Add(Library library){
        if(count >= array.length){
            ChangeArrayLength(defaultCount);
        }

        array[count++] = library;
        return true;
    }

    public Library Remove(){
        if(count <= 0)
            return null;

        var oldLibrary = array[0];
        LeftGo(0);

        if(array.length - defaultCount > count){
            ChangeArrayLength(-defaultCount);
        }

        return oldLibrary;
    }

    public Library Peek(){
        return array[count-1];
    }

    public void Write(){
        if(count <= 0){
            writer.WriteLine("Библиотек пока нет!");
            return;
        }
        for(int i = 0; i < count; i++){
            array[i].Write();
        }
    }

    public boolean AddBook(int libraryNumber, Book book){
        var index = Find(libraryNumber);
        if(index == -1)
            return false;

        return array[index].AddBook(book);
    }

    public Book RemoveBook(int libraryNumber, String bookName){
        var index = Find(libraryNumber);
        if(index == -1)
            return null;

        return array[index].RemoveBook(bookName);
    }

    public int Find(int libraryNumber){
        for(int i = 0; i < count; i++){
            if(array[i].getNumber() == libraryNumber){
                return i;
            }
        }
        return -1;
    }

    private void ChangeArrayLength(int length){
        var newArray = new Library[array.length + length];
        for(int i = 0; i < count; i++){
            newArray[i] = array[i];
        }
        array = newArray;
    }

    private void LeftGo(int index){
        for(int i = index; i < count; i++){
            array[i] = array[i + 1];
        }
        array[--count] = null;
    }
}
