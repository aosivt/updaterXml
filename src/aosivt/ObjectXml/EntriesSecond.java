package aosivt.ObjectXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by iskander on 03.12.16.
 */
@XmlRootElement(name="entries")
@XmlAccessorType(XmlAccessType.FIELD)

public class EntriesSecond {

    @XmlElement(name="entry")
    private EntrySecond[] entries;

    public EntrySecond[] getEntries() {
        return entries;
    }

    public void setEntries(EntrySecond[] entries) {
        this.entries = entries;
    }
}