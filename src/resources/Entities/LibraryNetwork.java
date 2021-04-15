package resources.Entities;

import Services.ConsoleWriter;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import resources.Abstractions.IWriter;
import resources.Collections.DynQueue;

import java.io.File;

public class LibraryNetwork {
    private DynQueue queue;
    private final String fileName = "D://MyDevelop//BulatProject//out.txt";

    public LibraryNetwork(IWriter _writer){
        queue = new DynQueue(_writer);
    }

    public boolean AddLibrary(Library library){
        return queue.Add(library);
    }

    public Library RemoveLibrary(){
        return queue.Remove();
    }

    public boolean AddBook(int libraryNumber, Book book){
        return queue.AddBook(libraryNumber, book);
    }

    public Book RemoveBook(int libraryNumber, String bookName){
        return queue.RemoveBook(libraryNumber, bookName);
    }

    public void Write(){
        queue.Write();
    }

    public int FindLibrary(int libraryNumber){
        return queue.Find(libraryNumber);
    }

    public void SaveToFile(){
        try {
            var objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(fileName), queue);
        }
        catch (JsonGenerationException e){
            e.printStackTrace();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void LoadFromFile(IWriter writer){
        try{
            var objectMapper = new ObjectMapper();
            queue = objectMapper.readValue(new File(fileName), DynQueue.class);
            queue.ChangeWriter(writer);
        }
        catch (JsonGenerationException e){
            e.printStackTrace();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return;
    }
}
