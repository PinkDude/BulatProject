package Services;

import resources.Abstractions.IWriter;

public class ConsoleWriter implements IWriter {
    @Override
    public void WriteLine(String text) {
        System.out.println(text);
    }

    @Override
    public void Write(String text) {
        System.out.print(text);
    }
}
