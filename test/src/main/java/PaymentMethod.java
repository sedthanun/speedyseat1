public abstract class PaymentMethod {
    // Method to process the payment
    public abstract void pay(double amount);

    // Method to get details of the payment method
    public abstract String getDetails();

    // Method to get the name of the payment method
    public abstract String getName();
}
