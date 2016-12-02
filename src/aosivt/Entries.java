package aosivt;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshchepkovayu on 02.12.16.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entries {
    @XmlElement(name="entry")
    protected Entry[] entries;

    public Entry[] getEntries() {
        return entries;
    }

    public void setEntries(long[] entries) {

        List<Entry> list_entry = new ArrayList<Entry>();
        Entry entry = null;
        for (int i = 0; i < entries.length; i++) {
            entry = new Entry();
            entry.setField(entries[i]);
            list_entry.add(entry);
            entry = null;
        }
        this.entries = ((List<Entry>)list_entry).toArray(new Entry[list_entry.size()]);
    }
}
