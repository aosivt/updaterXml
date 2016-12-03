package aosivt.ObjectXml;

import aosivt.InterfaceObjectXml.EntryInterface;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshchepkovayu on 02.12.16.
 */
@XmlRootElement(name="entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class Entries {
    @XmlElement(name="entry")
    private Entry[] entries;

    public Entry[] getEntries() {
        return entries;
    }

    public void setEntries(Entry[] entries) {
        this.entries = entries;
    }
}
