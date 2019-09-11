package ru.blogspot.toolkas.telegram.bot.echo;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * Класс-обработчик поступающих к боту сообщений.
 */
public class EchoBot extends TelegramLongPollingBot {

    @Override
    public String getBotToken() {
        return "981523630:AAHWIArCbJFBUbugbMEP0sy14sYXEWK9I2s";
    }


    @Override
    public void onUpdateReceived(Update update) {

        Map<String, String> requestReply = new HashMap<>();

        requestReply.put("Привет","Здравствуйте");
        requestReply.put("Как дела?","Великолепно");
        requestReply.put("Пока","До встречи!");
        requestReply.put("Что делаешь?","Жду сообщений");
        requestReply.put("Как погода?","То пасмурно, то солнечно");
        requestReply.put("Как настроение?", "Прекрасное");
        requestReply.put("Как работа?","Не так просто, как кажется");
        requestReply.put("Как тебя зовут?", "Dexsysbot");
        requestReply.put("Что ты умеешь?", "Практически ничего :(");

        Iterator<Map.Entry<String, String>> iterator = requestReply.entrySet().iterator();

        try {
            if (update.hasMessage() && update.getMessage().hasText()){
                //Извлекаем объект входящего сообщения
                Message inMessage = update.getMessage();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> pair = iterator.next();
                    if (pair.getKey().equalsIgnoreCase(inMessage.getText())){
                        SendMessage outMessage = new SendMessage();
                        //Указываем в какой чат будем отправлять сообщение
                        outMessage.setChatId(inMessage.getChatId());
                        //Указываем текст сообщения
                        outMessage.setText(pair.getValue());
                        //Отправляем сообщение
                        execute(outMessage);
                        break;
                    }
                    else if(iterator.hasNext() == false) {
                        //Создаем исходящее сообщение
                        SendMessage outMessage = new SendMessage();
                        //Указываем в какой чат будем отправлять сообщение
                        outMessage.setChatId(inMessage.getChatId());
                        //Указываем текст сообщения
                        outMessage.setText("Даже не знаю, что вам ответить.");
                        //Отправляем сообщение
                        execute(outMessage);
                    }
                }
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "DexFreeBot";
    }




     /*
        try {
            //проверяем есть ли сообщение и текстовое ли оно
            if (update.hasMessage() && update.getMessage().hasText()) {
                //Извлекаем объект входящего сообщения
                Message inMessage = update.getMessage();
                //Создаем исходящее сообщение
                SendMessage outMessage = new SendMessage();
                //Указываем в какой чат будем отправлять сообщение
                //(в тот же чат, откуда пришло входящее сообщение)
                outMessage.setChatId(inMessage.getChatId());
                //Указываем текст сообщения
                outMessage.setText(inMessage.getText());
                //Отправляем сообщение
                execute(outMessage);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        */
}
