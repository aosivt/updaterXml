package aosivt.BD;

/**
 * Created by iskander on 03.12.16.
 */
import aosivt.InterfaceObjectXml.EntryInterface;
import aosivt.ObjectXml.Entry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;
import java.util.Scanner;


public class ClassForConnectBD {
private String host;
private String port;
private String dbase;
private String user;
private String password;
private String N;
private String query_delete;
private String query_insert;
private String query_select;
private String query_select_check;
private Connection connection_to_db;

    public ClassForConnectBD(String _host,String _port,String _user,String _password,String _dbase, String _N)
    {
        this.host = _host;
        this.port = _port;
        this.user = _user;
        this.password = _password;
        this.dbase = _dbase;
        this.N = _N;



        this.query_delete = "delete * from public.test";
        this.query_insert = "insert into public.test select * from generate_series(1,"+_N+ ") as field";
        this.query_select = "select * from public.test";
        this.query_select_check = "select exists(select test.field from public.test where field=1) as check";

        this.connection_to_db = this.setConnection_to_db();
    }

    public Connection getConnection_to_db() {
        return connection_to_db;
    }

    private Connection setConnection_to_db() {


        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://"+this.host+
                            ":"+this.port+"/"
                            +this.dbase,
                    this.user,
                    this.password);

        } catch (SQLException e) {

            System.out.println("Подключение провалено! Проверьте данные консоли");
            e.printStackTrace();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void conn() throws SQLException {
        if (this.connection_to_db != null) {

            System.out.println("Есть подключение");

            this.insert_to_db();


        } else {
            System.out.println("Подключение провалено");
        }

    }



    public void insert_to_db()
    {
        try {
            Statement st = this.getConnection_to_db().createStatement();

            if (checkExistRecordFromDB(st))
            {
                st.execute(this.query_delete);
            }

            st.execute(this.query_insert);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public Long[] getSelectFromTest() throws SQLException {
        Statement st = this.getConnection_to_db().createStatement();
        ResultSet rs =  st.executeQuery(this.query_select);
        List<Long> longs = new ArrayList<>();
        while (rs.next())
        {
            longs.add(rs.getLong("field"));
        }

        this.closeConnection();

        return ((List<Long>)longs).toArray(new Long[longs.size()]);
    }

    private boolean checkExistRecordFromDB(Statement _st) throws SQLException {

        ResultSet rs =  _st.executeQuery (this.query_select_check);

        return rs.getBoolean("check");
    }

    private void closeConnection() throws SQLException {
        this.getConnection_to_db().close();
    }


}







//
//    Statement st = conn.createStatement();
//    ResultSet rs = st.executeQuery("SELECT * FROM mytable WHERE columnfoo = 500");
//while (rs.next())
//        {
//        System.out.print("Column 1 returned ");
//        System.out.println(rs.getString(1));
//        } rs.close();
//        st.close();


