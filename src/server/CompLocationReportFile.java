package server;

import javax.jws.WebService;

@WebService
public interface CompLocationReportFile {

    public abstract void getAllUpdateItem(StringBuffer path);
}
