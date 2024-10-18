public class PayByQR extends PaymentMethod {
    // Pay by QR method
    protected PaymentUI.qrCodePanel payByQRCodeUI;

    @Override
    public void pay(double amount) {
    }

    // Get details for QR payment method
    @Override
    public String getDetails() {
        return getDetails();
    }

    // Get name for QR payment method
    @Override
    public String getName() {
        return getName();
    }
}
