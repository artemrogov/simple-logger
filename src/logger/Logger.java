package logger;

import java.io.*;
import java.util.Date;
import java.util.Scanner;


public class Logger {

    private static String[] displayMenu(){

        return new String[]{
                "-----------------------Меню--------------------",
                "timelog - записать лог сообщения в файл с временем",
                "filelog - записать лог сообщения в файл",
                "consolelog - записать лог сообщения в консоль",
                "exit - выйти из программы ",
                "-------------------------------------------------"
        };

    }

    public static void main(String[] args) {

        String message;

        Scanner msg;

        String command;

        Scanner commandScaner;

        LoggerStrategyClient clientScanner = new LoggerStrategyClient();

        for (String item: displayMenu()){
            System.out.println(item);
        }

        do {
            commandScaner = new Scanner(System.in);

            command = commandScaner.nextLine();
            if (command.equals("timelog")){
                System.out.println("Введите сообщение для логгирования:");
                msg = new Scanner(System.in);
                message = msg.nextLine();
                TimeFileLogger tmMessage = new TimeFileLogger();
                tmMessage.setMessage(message);
                clientScanner.setLogger(tmMessage);


            }else if(command.equals("consolelog")){
                System.out.println("Введите сообщение для логгирования:");
                LoggerStrategyClient consoleStrategy = new LoggerStrategyClient();

                ConsoleLogger consoleLogger = new ConsoleLogger();

                consoleStrategy.setLogger(consoleLogger);

            }else if (command.equals("filelog")){
                System.out.println("Введите сообщение для логгирования:");
                FileLogger fileLogger = new FileLogger();
                LoggerStrategyClient loggerFile = new LoggerStrategyClient();
                loggerFile.setLogger(fileLogger);
            }

        }while(!command.equals("exit"));

    }
}
interface ILogger {

    void write();
}

class LoggerStrategyClient{

    private ILogger logger;

    public void setLogger(ILogger logger) {

        this.logger = logger;

        this.logger.write();


    }

}

class ConsoleLogger implements ILogger {

    @Override
    public void write() {
        System.out.println("Изменения записаны!");
    }
}

class  FileLogger implements ILogger {
    @Override
    public void write() {
        File fileLog;

        try {

            fileLog = new File("logger.log");
            PrintWriter writer = new PrintWriter(fileLog);
            writer.println("Запись изменений в файл!");
            writer.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

class TimeFileLogger implements ILogger {

    private String message;


    @Override
    public void write() {
        File fileLog;
        Date date;

        try {
            fileLog = new File("messageLog.log");

            OutputStream out = new FileOutputStream(fileLog,true);
            date = new Date();
            String data = getMessage() + date.toString();

            byte[] msg = data.getBytes();

            out.write(msg,0,msg.length);

            out.close();

        }catch (IOException e){
            e.printStackTrace();
        }



    }

    public  void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){

        return this.message;
    }
}
