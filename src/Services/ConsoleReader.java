package Services;

import resources.Abstractions.IReader;
import resources.Abstractions.IWriter;

import java.util.Scanner;

public class ConsoleReader implements IReader {
    private Scanner scanner = new Scanner(System.in);
    private IWriter writer = new ConsoleWriter();

    @Override
    public int ReadInt() {
        writer.Write("Введите число: ");
        return scanner.nextInt();
    }

    @Override
    public String ReadString() {
        writer.Write("Введите строку: ");
        return scanner.next();
    }
}
