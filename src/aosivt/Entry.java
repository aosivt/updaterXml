package aosivt;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Created by oshchepkovayu on 02.12.16.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Entry {
    protected long field;

    public Entry()
    {

    }
    public long getField() {
        return field;
    }

    public void setField(long field) {
        this.field = field;

    }
}
