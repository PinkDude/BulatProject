package resources.Entities;

import resources.Abstractions.IReader;
import resources.Abstractions.IWriter;

import java.util.Scanner;

public class Book implements Comparable<Book> {
    private String name;
    private int count;
    private IWriter writer;

    public Book(){

    }

    public Book(IWriter _writer){
        writer = _writer;
    }

    public Book(IWriter _writer, String _name, int _count){
        writer = _writer;
        name = _name;
        count = _count;
    }

    public String getName(){
        return name;
    }

    public void setName(String _name){
        name = _name;
    }

    public int getCount(){
        return count;
    }

    public void setCount(int _count){
        count = _count;
    }

    public void ChangeWriter(IWriter _writer){
        writer = _writer;
    }

    public void Write(){
        var text = "Книга " + name + " - " + count;
        writer.WriteLine(text);
    }

    public static Book CreateBook(IWriter writer, IReader reader){
        writer.WriteLine("Какое название у книги?");
        var bookName = reader.ReadString();
        writer.WriteLine("Какое количество?");
        var bookCount = reader.ReadInt();
        return new Book(writer, bookName, bookCount);
    }

    @Override
    public String toString(){
        return "(" + name + ")";
    }

    @Override
    public int compareTo(Book o) {
        return toString().compareTo(o.toString());
    }
}
