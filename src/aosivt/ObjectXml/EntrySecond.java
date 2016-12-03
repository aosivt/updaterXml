package aosivt.ObjectXml;

import aosivt.InterfaceObjectXml.EntryInterface;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by iskander on 03.12.16.
 */
@XmlType(propOrder = {"field"})
public class EntrySecond implements EntryInterface {
    protected long field;

    public EntrySecond()
    {

    }
    @XmlAttribute
    public long getField() {
        return field;
    }

    public void setField(long field) {
        this.field = field;

    }
}
