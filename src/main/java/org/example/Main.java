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


//            MyWriter myWriter = entityManager.find(MyWriter.class, 1000);
//            try {
//                if (myWriter.equals(null)) {
//                }
//                else {
//                    System.out.println(myWriter);
//                }
//            }
//            catch (NullPointerException e) {
//                System.out.println("\nsuch id not exist // ERROR: " + e.getMessage());
//                e.printStackTrace();
//            }


//===========================================================

            //FIND получаем (ВСЕ) данные из таблицы в БД (СПИСОК)

            String name;
            List<MyWriter> list = entityManager.createQuery("SELECT w FROM MyWriter w").getResultList();   // язык запроса JPQL (Java Persistence Query Language) или HQL
            for (int i = 0; i < list.size(); i++) {
//                System.out.println(list.get(i));

                name = String.valueOf(list.get(i).getId());
                name = name.concat("_" + list.get(i).getFirstName());
//                System.out.println(name);

                FileWriter fw = new FileWriter("C:\\Users\\dmitr\\Desktop\\ТЕСТ\\" + name + ".sql");  // формат можно .txt, .sql
                fw.write(String.valueOf(list.get(i).getWeb()));
//                String content = String.valueOf(list.get(i).getWeb());
//                System.out.println(content);
                fw.close();
            }


//===========================================================
            // UPDATE изменить данные


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