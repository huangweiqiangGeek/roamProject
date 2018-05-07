package server;

import javax.jws.WebService;

@WebService
public interface CompLocationReport {

    public abstract void getAllUpdateItem(StringBuffer path);
}
