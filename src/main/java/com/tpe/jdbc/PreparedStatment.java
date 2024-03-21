package com.tpe.jdbc;

import java.sql.*;

public class PreparedStatment {
    public static void main(String[] args) throws SQLException {
        //!!! 2.ADIM : Hangi DB, Hangi kullanici ve sifre
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_dt",
                "techprodt",
                "password");

        //!!! 3.ADIM : statement olusturma, yazacagimiz queryleri statement nesnesi ile DB ye gonderecegiz.
        Statement st = con.createStatement();
        System.out.println("success");

        //!!!   ÖRNEK1: bolumler tablosunda Matematik bölümünün taban_puanı'nı 475 olarak güncelleyiniz.
        String sql1 = "UPDATE bolumler SET taban_puani = 475 WHERE bolum ILIKE 'Matematik'";
        int updated = st.executeUpdate(sql1);
        //System.out.println("updated " + updated);

        //parametreli sorgu ile yazalim
        //!!!   ÖRNEK2: bolumler tablosunda Matematik bölümünün taban_puanı'nı 499 olarak güncelleyiniz.
        String sql2 = "UPDATE bolumler SET taban_puani =? WHERE bolum ILIKE ?";
        PreparedStatement prst = con.prepareStatement(sql2);
        prst.setInt(1, 499);
        prst.setString(2, "Matematik");
        int updated2 = prst.executeUpdate();
        //System.out.println("updated2 : " + updated2);

        //!!!   Örnek3: Prepared Statement kullanarak bolumler tablosunda Edebiyat bölümünün taban_puanı'nı
        // 477 olarak güncelleyiniz.

        prst.setString(2, "Edebiyat");
        prst.setInt(1,477);
        int updated3 = prst.executeUpdate();
        System.out.println("updated3 : " + updated3);

        //!!!   Örnek 4:Prepared Statement kullanarak bolumler tablosuna
        // Yazılım Mühendisliği(id=5006,taban_puanı=475, kampus='Merkez') bölümünü ekleyiniz.

        String sql3 = "INSERT INTO bolumler(bolum_id, bolum, taban_puani, kampus) VALUES (?,?,?,?)";
        PreparedStatement prst1 = con.prepareStatement(sql3);
        prst1.setInt(1, 5006);
        prst1.setString(2, "Yazılım mühendisliği");
        prst1.setInt(3, 475);
        prst1.setString(4, "Merkez");
        int updated4 = prst1.executeUpdate();
        System.out.println("updated4 : " + updated4);

        //---------workers tablosuna eleman ekleme
        String sql4 = "INSERT INTO workers VALUES (?,?,?,?)";


        st.close();
        con.close();
    }
}
