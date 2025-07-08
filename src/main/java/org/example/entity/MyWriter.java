package org.example.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data                                                   // @Data - автоматически генерирует ГЕТТЕРЫ и СЕТТЕРЫ для всех НЕ финальных полей, МЕТОДЫ equals() и hashCode() и МЕТОД toString().
@NoArgsConstructor                                      // @NoArgsConstructor - заменяет Конструктор БЕЗ параметров (библиотека lombok)
@AllArgsConstructor
@Entity                                                 // @Entity - это Java Класс, который отображает информацию определенной таблицы в БД
@Table(name = "employees")                              // @Table(name = "clubs") - имя таблицы в БД. Показывает к какой именно таблице мы привязываем Класс
public class MyWriter {

    @Id                                                 // @Id - указывает какой столбец таблицы является PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue - автогенерация PRIMARY KEY     // IDENTITY - стратегия, при которой PRIMARY KEY изменяется в соответствии с правилами, прописанными при создании таблицы
    private int id;

    @Column(name = "first_name")                        // @Column(name = "first_name") - имя столбца в таблице. Делает привязку (mapping) переменной класса к столбцу таблицы ДБ=
    private String firstName;

    @Column(name = "last_name")                         // @Column(name = "last_name") - имя столбца в таблице. Делает привязку (mapping) переменной класса к столбцу таблицы ДБ=
    private String lastName;

    @Column(name = "company_name")
    private String companyName;

    private String address;
    private String city;
    private String county;
    private String state;
    private int zip;
    private String phone1;
    private String phone2;
    private String email;
    private String web;


    public MyWriter(String firstName, String lastName, String companyName, String address, String city, String county, String state, int zip, String phone1, String phone2, String email, String web) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.address = address;
        this.city = city;
        this.county = county;
        this.state = state;
        this.zip = zip;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.web = web;
    }



    @Override
    public String toString() {
        return "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", email='" + email + '\'' +
                ", web='" + web + '\'';
    }
}
