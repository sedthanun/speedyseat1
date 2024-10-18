import java.util.ArrayList;
import java.util.List;

public class Payment {
    // Attribute: List of PaymentMethod (Aggregation)
    private List<PaymentMethod> paymentMethods;

    // Constructor
    public Payment() {
        this.paymentMethods = new ArrayList<>();
    }

    // Getter for payment methods
    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    // Make a payment using a specific payment method
    public void makePayment(PaymentMethod paymentMethod) {

    }
}
