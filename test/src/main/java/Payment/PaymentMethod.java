package Payment;

public abstract class PaymentMethod extends javax.swing.JPanel {
    // Abstract method to process the payment
    abstract void pay(double amount);

    // Abstract method to get details of the payment method
    abstract String getDetails();

    // Abstract method to get the name of the payment method
    abstract String getPaymentMethodName();

    // Get name for cash payment method
    public abstract String PaymentMethod();
}
