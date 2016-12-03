package aosivt;

import aosivt.BD.ClassForConnectBD;
import aosivt.InterfaceObjectXml.EntryInterface;
import aosivt.ObjectXml.Entries;
import aosivt.ObjectXml.EntriesSecond;
import aosivt.ObjectXml.Entry;
import aosivt.ObjectXml.EntrySecond;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.bind.Binder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
	if (args.length>5)
    {
        System.out.print("Не указаны входные параметры");
        System.exit(101);
    }
        ClassForConnectBD classForConnectBD =
                new ClassForConnectBD(
                                        args[0], //host
                                        args[1], //port
                                        args[2], //user
                                        args[3], //password
                                        args[4], //dbase_name
                                        args[5]  //количество записей (Число N)
                                        );

        classForConnectBD.conn();

        Entries entries = new Entries();



//        Long[]  _field = new Long[]{1L,2L,3L,4L,5L,6L,7L,8L,9L,10L};
        Long[]  _field = classForConnectBD.getSelectFromTest();

        entries.setEntries(getDifEntry(_field));


        try {

            File file = new File("report"+ File.separator + "1.xml");
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

//            XmlReader reader = XmlReader.Create(strXMLFile);
//            XslCompiledTransform objXSLTransform = new XslCompiledTransform();
//            objXSLTransform.Load(strXSLTFile);

            // we need a blank document to store final xml output
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();
            Document document_input_xml = docBuilder.parse("report"+ File.separator + "1.xml");

            Transformer xformer = TransformerFactory.newInstance()
                                  .newTransformer(
            new StreamSource("report"+ File.separator + "ConvertingXML.xsl")
                                                  );

            xformer.setOutputProperty(OutputKeys.METHOD, "xml");
            xformer.setOutputProperty(OutputKeys.INDENT, "yes");
            xformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"no");
            xformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");


            Source source = new DOMSource(document_input_xml);
            Result result = new StreamResult(new File("report"+ File.separator + "2.xml"));
            xformer.transform(source, result);


            Document document_result_xml = docBuilder.parse("report"+ File.separator + "2.xml");

            JAXBContext jc = JAXBContext.newInstance(EntriesSecond.class);

            Binder<Node> nodeBinder = jc.createBinder();

            Node node = document_result_xml.getDocumentElement();


            EntriesSecond entries1 = (EntriesSecond) nodeBinder.unmarshal(node);
            long last_field = ((EntrySecond) entries1.getEntries()[entries1.getEntries().length-1]).getField();
            long result_test = (last_field*(last_field+1))/2;

System.out.println("Результат сложение числового ряда =  " + Long.toString(result_test));
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Entry[] getDifEntry(Long[] _field_entries)
    {

        List<EntryInterface> list_entry = new ArrayList<>();
        EntryInterface entry = null;
        for (int i = 0; i < _field_entries.length; i++) {
            entry = new Entry();
            entry.setField(_field_entries[i]);
            list_entry.add(entry);
            entry = null;
        }
        return ((List<EntryInterface>)list_entry).toArray(new Entry[list_entry.size()]);
    }
}
