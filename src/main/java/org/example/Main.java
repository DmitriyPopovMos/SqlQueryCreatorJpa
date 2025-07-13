package org.example;

import jakarta.persistence.*;
import org.example.entity.MyWriter;
import java.io.FileWriter;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SqlQueryCreatorJpa");
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();                                                    // начало транзакции

//===========================================================
            // PERSIST заполняем таблицу в Базе Данных
//
//            MyWriter myWriter = new MyWriter("TEST1", "TEST1", "TEST1", "TEST1", "TEST1",
//                    "TEST1", "TEST1", 123, "504-845-1427", "12-66-44", "TEST1@gmail.com",
//                    "http://www.TEST1.com");
//
//            entityManager.persist(myWriter);


//===========================================================
            //FIND получаем данные из таблицы в БД по id

            String name;
            int maxLength = 143;                    // Файловая система Astra Linux имеет eCryptFS, которое устанавливает ограничение на имя файла в 143 символа.

            MyWriter myWriter = entityManager.find(MyWriter.class, 1000);
            try {
                name = String.valueOf("N_" + myWriter.getId());
                name = name.concat("_" + myWriter.getFirstName());
                name = name.replaceAll("[^\\w]+", " ");                                // "[^\w]+" - удаляем все специальные символы

                System.out.println("\nNative name.length: " + name.length());
                System.out.println(name);

                if (name.length() > maxLength) {
                    FileWriter fw = new FileWriter("C:\\Users\\dmitr\\Desktop\\ТЕСТ\\" + name + ".sql");  // формат можно .txt, .sql
                    fw.write(String.valueOf(myWriter.getWeb()));
                    fw.close();
//                    System.out.println(myWriter);
                }
                else {
                    FileWriter fw = new FileWriter("C:\\Users\\dmitr\\Desktop\\ТЕСТ\\" + name + ".sql");  // формат можно .txt, .sql
                    fw.write(String.valueOf(myWriter.getWeb()));
                    fw.close();
                    //                System.out.println(myWriter);
                }
                System.out.println("Resume name.length: " + name.length());
            }
            catch (NullPointerException e) {
                System.out.println("\nsuch id not exist // ERROR: " + e.getMessage());
                e.printStackTrace();
            }


//===========================================================

            //FIND получаем (ВСЕ) данные из таблицы в БД (СПИСОК)

//            int count = 0;
//            String name;
//            int maxLength = 143;                         // Файловая система Astra Linux имеет eCryptFS, которое устанавливает ограничение на имя файла в 143 символа.
//
//            //*** Выборка данных из таблицы БД (ВСЕ файлы)***
//            List<MyWriter> list = entityManager.createQuery("SELECT w FROM MyWriter w WHERE 1 = 1 " +
//                    "ORDER BY w.id ASC").getResultList();                                                     // язык запроса JPQL (Java Persistence Query Language) или HQL
//
//            //*** Выборка данных из таблицы БД (По запросу с условием)***
////            List<MyWriter> list = entityManager.createQuery("SELECT w FROM MyWriter w WHERE 1 = 1 " +
////                    "AND w.state = 1" +
////                    "AND w.request ilike '%between%'" +
////                    "AND regexp_replace(w.request, ' ', '', 'g') not ilike '%::datebetween%'" +
////                    "AND w.request not ilike '%23:59:59%'" +
////                    "AND regexp_replace(w.request, ' ', '', 'g') not ilike '%receiveddatebetween%'").getResultList();   // язык запроса JPQL (Java Persistence Query Language) или HQL
//
//            for (int i = 0; i < list.size(); i++) {
//                count++;
////                System.out.println(list.get(i));
//
//                name = String.valueOf("N_" + list.get(i).getId());
//                name = name.concat("_" + list.get(i).getFirstName());
//                name = name.replaceAll("[^\\w]+", " ");                             // "[^\w]+" - удаляем все специальные символы
//
//                if (name.length() > maxLength) {
//                    name = name.substring(0, maxLength);
//
//                    FileWriter fw = new FileWriter("C:\\Users\\dmitr\\Desktop\\ТЕСТ\\" + name + ".sql");  // формат можно .txt, .sql
//                    fw.write(String.valueOf(list.get(i).getWeb()));
//                    fw.close();
////                    System.out.println(list.get(i));
//                } else {
//
//                    FileWriter fw = new FileWriter("C:\\Users\\dmitr\\Desktop\\ТЕСТ\\" + name + ".sql");  // формат можно .txt, .sql
//                    fw.write(String.valueOf(list.get(i).getWeb()));
//                    fw.close();
////                    System.out.println(list.get(i));
//                }
//            }
//            System.out.println("\nCount: " + count);


//===========================================================
            // UPDATE (merge) изменить данные


//            MyWriter myWriter = new MyWriter(1006, "TEST13", "TEST13", "TEST11", "TEST11", "TEST11",
//                    "TEST11", "TEST11", 123, "504-845-1427", "12-66-44", "TEST11@gmail.com",
//                    "http://www.TEST11.com");
//            entityManager.merge(myWriter);

//===========================================================

            //REMOVE удаляет данные из таблицы в БД

//            MyWriter myWriter = entityManager.find(MyWriter.class, 1008);
//            entityManager.remove(myWriter);


//===========================================================

            transaction.commit();                                                      // конец транзакции
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
                factory.close();
            }
        }
    }
}