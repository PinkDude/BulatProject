package Services;

import resources.Abstractions.IReader;
import resources.Abstractions.IWriter;
import resources.Entities.Book;
import resources.Entities.Library;
import resources.Entities.LibraryNetwork;

import java.util.Scanner;

public class MainService {
    private IReader reader = new ConsoleReader();
    private IWriter writer = new ConsoleWriter();
    private LibraryNetwork libraryNetwork = new LibraryNetwork(new ConsoleWriter());

    public void Main(){
        while(true){
            WriteBaseMessage();
            var answer = reader.ReadInt();
            switch (answer) {
                case 1: {
                    WriteLibraryNetwork();
                    break;
                }
                case 2: {
                    AddLibrary();
                    break;
                }
                case 3: {
                    RemoveLibrary();
                    break;
                }
                case 4: {
                    AddBook();
                    break;
                }
                case 5:{
                    RemoveBook();
                    break;
                }
                case 6:{
                    SaveToFile();
                    break;
                }
                case 7:{
                    LoadFromFile();
                    break;
                }
                default:{
                    writer.WriteLine("Такого значения нет! Попробуйте еще раз...");
                    break;
                }
            }
        }
    }

    private void LoadFromFile(){
        libraryNetwork.LoadFromFile(writer);
    }

    private void SaveToFile(){
        libraryNetwork.SaveToFile();
    }

    private void RemoveBook(){
        writer.WriteLine("Из библиотеки под каким номером нужно удалить книгу?");
        var libraryNumber = reader.ReadInt();
        if(libraryNetwork.FindLibrary(libraryNumber) == -1){
            writer.WriteLine("Нет библиотеки с таким номером!");
            return;
        }

        writer.WriteLine("Книгу с каким название нужно удалить?");
        var bookName = reader.ReadString();

        var book = libraryNetwork.RemoveBook(libraryNumber, bookName);
        if(book == null)
            writer.WriteLine("Не удалось удалить книгу. Возможно нет такой книги...");
    }

    private void AddBook(){
        writer.WriteLine("В библиотеку под каким номером нужно добавить книгу?");
        var libraryNumber = reader.ReadInt();
        var book = Book.CreateBook(writer, reader);
        libraryNetwork.AddBook(libraryNumber, book);
    }

    private void RemoveLibrary(){
        var library = libraryNetwork.RemoveLibrary();
        if(library == null){
            writer.WriteLine("Не удалось удалить библиотеку. Возможно нет библиотеки с таким номером...");
            return;
        }
    }

    private void WriteLibraryNetwork(){
        libraryNetwork.Write();
    }

    private void AddLibrary(){
        var library = Library.CreateLibrary(writer, reader);
        libraryNetwork.AddLibrary(library);
    }

    private void WriteBaseMessage(){
        writer.WriteLine("1) Вывод на консоль\n" +
                "2) Добавить новую библиотеку\n" +
                "3) Удалить библиотеку\n" +
                "4) Добавить новую книгу\n" +
                "5) Удалить книгу\n" +
                "6) Сохранить в файл\n" +
                "7) Загрузить из файла\n");
    }
}
