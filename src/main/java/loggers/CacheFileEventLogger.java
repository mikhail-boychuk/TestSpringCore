package loggers;

import beans.Event;

import java.util.List;
import java.util.ArrayList;

public class CacheFileEventLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String filename, int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<Event>(cacheSize);
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
           // writeEventsFromCache();
            for (Event e : cache) {
                super.logEvent(e);
            }
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        for (Event e : cache) {
            super.logEvent(e);
        }
    }

    private void destroy () {
        if (!cache.isEmpty()) {
            //writeEventsFromCache();
            for (Event e : cache) {
                super.logEvent(e);
            }
        }
    }
}
