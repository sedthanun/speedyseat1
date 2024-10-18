package Theatre;

import java.util.Dictionary;
import java.util.Hashtable;

public class Theatre {
    private int theatreID;
    private String theatreBranch;
    private int theatreNumber;

    public Theatre(int theatreID, String theatreBranch, int theatreNumber){
        this.theatreID = theatreID;
        this.theatreBranch = theatreBranch;
        this.theatreNumber = theatreNumber;
    }

    public Dictionary<String, Object> getTheatreInfo() {
        Dictionary<String, Object> theatreInfo = new Hashtable<>();

        theatreInfo.put("theatreID", theatreID);
        theatreInfo.put("theatreBranch", theatreBranch);
        theatreInfo.put("theatreNumber", theatreNumber);

        return theatreInfo;
    }
}
