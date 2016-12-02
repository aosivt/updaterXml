package aosivt;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.bind.Binder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Main {

    public static void main(String[] args) {
	if (args.length==0)
    {
        System.out.print("Не указаны входные параметры");
        System.exit(101);
    }

        Entries entries = new Entries();



        long[]  _field = new long[]{1,2,3,4,5,6,7,8,9,10};
        entries.setEntries(_field);


        try {

            File file = new File("1.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(entries, file);
            jaxbMarshaller.marshal(entries, System.out);




        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {


            // we need a blank document to store final xml output
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();
            Document document = docBuilder.parse("1.xml");

            JAXBContext jc = JAXBContext.newInstance(Entries.class);

            Binder<Node> nodeBinder = jc.createBinder();

            Node node = document.getDocumentElement();


            Entries entries1 = (Entries) nodeBinder.unmarshal(node);
            long last_field = ((Entry) entries1.getEntries()[entries1.getEntries().length-1]).getField();
            long result = (last_field*(last_field+1))/2;




            node.getAttributes();




        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
//    public static Element getLastChildElement(Node parent) {
//
//        // search for node
//        Node child = parent.getLastChild();
//        while (child != null) {
//            if (child.getNodeType() == Node.ELEMENT_NODE) {
//                return (Element)child;
//            }
//            child = child.getPreviousSibling();
//        }
//
//        // not found
//        return null;
//
//    } // getLastChildElement(Node):Element


/*
import java.sql.DriverManager;
        import java.sql.Connection;
        import java.sql.SQLException;

public class JDBCExample {

    public static void main(String[] argv) {

        System.out.println("-------- PostgreSQL "
                + "JDBC Connection Testing ------------");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return;

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/testdb", "mkyong",
                    "123456");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

}*/


//
//    Statement st = conn.createStatement();
//    ResultSet rs = st.executeQuery("SELECT * FROM mytable WHERE columnfoo = 500");
//while (rs.next())
//        {
//        System.out.print("Column 1 returned ");
//        System.out.println(rs.getString(1));
//        } rs.close();
//        st.close();

//SELECT * from generate_series(1,1000) as field

//    WITH RECURSIVE nums AS
//        (SELECT 1 AS value
//                UNION ALL
//                SELECT value + 1 AS value
//                FROM nums
//                WHERE nums.value <= 99)
//SELECT *
//        FROM nums


//    INSERT INTO films SELECT * FROM tmp_films WHERE date_prod < '2004-05-07';


//Конвертировать объект d xml
//package com.mkyong.core;
//
//        import java.io.File;
//        import javax.xml.bind.JAXBContext;
//        import javax.xml.bind.JAXBException;
//        import javax.xml.bind.Marshaller;
//
//public class JAXBExample {
//    public static void main(String[] args) {
//
//        Customer customer = new Customer();
//        customer.setId(100);
//        customer.setName("mkyong");
//        customer.setAge(29);
//
//        try {
//
//            File file = new File("C:\\file.xml");
//            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//
//            // output pretty printed
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//            jaxbMarshaller.marshal(customer, file);
//            jaxbMarshaller.marshal(customer, System.out);
//
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//
//    }
//}

// Конвертирование xml в объект
//package com.mkyong.core;
//
//        import java.io.File;
//        import javax.xml.bind.JAXBContext;
//        import javax.xml.bind.JAXBException;
//        import javax.xml.bind.Unmarshaller;
//
//public class JAXBExample {
//    public static void main(String[] args) {
//
//        try {
//
//            File file = new File("C:\\file.xml");
//            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
//
//            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            Customer customer = (Customer) jaxbUnmarshaller.unmarshal(file);
//            System.out.println(customer);
//
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
//
//    WITH RECURSIVE nums AS
//        (SELECT 1 AS value
//                UNION ALL
//                SELECT value + 1 AS value
//                FROM nums
//                WHERE nums.value <= 99)
//SELECT *
//        FROM nums

//SELECT * from generate_series(1,1000) as field