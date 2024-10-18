public interface PaymentMethod {
    // Method to process the payment
    void pay(double amount);

    // Method to get details of the payment method
    String getDetails();

    // Method to get the name of the payment method
    String getName();
}
