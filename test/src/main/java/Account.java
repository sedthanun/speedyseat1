import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Account {
    private String accountName;
    private int accountID;
    private List<Booking> bookings;

    public Account(String accountName, int accountID){
        this.accountName = accountName;
        this.accountID = accountID;
        this.bookings = new ArrayList<Booking>();
    }
    public Dictionary<String, Object> getAccountInfo() {
        Dictionary<String, Object> accountInfo = new Hashtable<>();

        accountInfo.put("accountName", accountName);
        accountInfo.put("accountID", accountID);
        accountInfo.put("bookings", bookings);

        return accountInfo;
    }

}
