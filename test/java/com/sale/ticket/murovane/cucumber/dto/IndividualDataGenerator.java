package com.sale.ticket.murovane.cucumber.dto;

import com.sale.ticket.murovane.model.ManName;
import com.sale.ticket.murovane.model.Surname;
import com.sale.ticket.murovane.model.WomanName;

import java.util.ArrayList;
import java.util.List;

public class IndividualDataGenerator {

    public List<ManName> createListManName(){
        List<ManName> list=new ArrayList<>();
        list.add(new ManName(1, "Андрей"));
        list.add(new ManName(2,"Давид"));
        list.add(new ManName(3,"Иван"));
        list.add(new ManName(4,"Тимофей"));
        list.add(new ManName(5,"Владимир"));
        list.add(new ManName(6,"Марк"));
        list.add(new ManName(7,"Богдан"));
        list.add(new ManName(8,"Назар"));
        list.add(new ManName(9,"Даниил"));
        list.add(new ManName(10,"Матвей"));
        return list;
    }

    public List<WomanName> createListWomanName(){
        List<WomanName> list = new ArrayList<>();
        list.add(new WomanName(1, "Злата"));
        list.add(new WomanName(2,"Таисия"));
        list.add(new WomanName(3,"Василиса"));
        list.add(new WomanName(4,"Зоя"));
        list.add(new WomanName(5,"Дарина"));
        list.add(new WomanName(6,"Мирослава"));
        list.add(new WomanName(7,"Дарина"));
        list.add(new WomanName(8,"Мирослава"));
        list.add(new WomanName(9,"Анастасия"));
        list.add(new WomanName(10,"София"));
        return list;
    }

    public List<Surname> createListSurname(){
        List<Surname> list = new ArrayList<>();
        list.add(new Surname(1,"Мельник"));
        list.add(new Surname(2,"Шевченко"));
        list.add(new Surname(3,"Бондаренко"));
        list.add(new Surname(4,"Коваленко"));
        list.add(new Surname(5,"Ткаченко"));
        list.add(new Surname(6,"Иванов"));
        list.add(new Surname(7,"Мороз"));
        list.add(new Surname(8,"Петренко"));
        list.add(new Surname(9,"Клименко"));
        list.add(new Surname(10,"Павленко"));
        return list;
    }
}
