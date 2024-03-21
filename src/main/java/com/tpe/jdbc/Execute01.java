package com.tpe.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //!!! 1.ADIM : Driver'i ekleme
        Class.forName("org.postgresql.Driver"); //JDK 7 ile birlikte gerek kalmadı

        //!!! 2.ADIM : Hangi DB, Hangi kullanici ve sifre
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_dt", "techprodt", "password");

        //!!! 3.ADIM : statement olusturma, yazacagimiz queryleri statement nesnesi ile DB ye gonderecegiz.
        Statement st = con.createStatement();
        System.out.println("success");

        //!!! 4.ADIM : sorguları olusturma

        //ORNEK 1 : "workers" adinda bir tablo olusturup "worker_id, worker_name, salary" sütunlarını ekleyin
        boolean sql1 = st.execute("CREATE TABLE IF NOT EXISTS workers(" +
                "worker_id INTEGER, " +
                "worker_name VARCHAR(50), " +
                "salary REAL)") ;
        System.out.println("SQL1 " + sql1);

          /*
            execute:tüm sorguları çalıştırmak için kullanılır
             sorgunun sonucunda ResultSet alınıyorsa TRUE döndürür, aksi halde FALSE döndürür.
             1-DDL (Data Definition Language) -->FALSE ( Create gibi sorgular )
             2-DQL (Data Query Language) -->TRUE (Select gibi sorgular )
             2-DML (Data Manipulation Language) --> FALSE ( update, insert gibi sorgular )
            genellikle DDL için kullanılır.
         */

        // ORNEK 2 : "workers" tablosuna VARCHAR(20) tipinde "city sütununu ekleyelim
        //bu kodu ikinci defa çalıştırdığımızda hata verecektir.
        // Çünkü önceden oluşturduğu kolonu bir daha oluşturmak isteyecektir.
        // bu da soruna sebep olur.
          st.execute("ALTER TABLE workers ADD COLUMN city VARCHAR(20)");

        //ORNEK 3 : workers tablosunu silelim
        st.execute("DROP TABLE workers");

        //!!! 5.ADIM : kaynaklari kapatıyoruz
        st.close();
        con.close();
    }
}
