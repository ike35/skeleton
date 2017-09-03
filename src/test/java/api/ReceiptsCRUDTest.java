package api;


import io.dropwizard.jersey.validation.Validators;
import org.junit.Test;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.math.BigInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;

public class ReceiptsCRUDTest {

    private final Validator validator = Validators.newValidator();

    @Test
    public void testValid() {
        CreateReceiptRequest receipt = new CreateReceiptRequest();
        receipt.merchant = "Macbook";
        receipt.amount = new BigDecimal(2000.00);
        assertThat(validator.validate(receipt), empty());
    }

    @Test
    public void testMissingAmount() {
        CreateReceiptRequest receipt = new CreateReceiptRequest();
        receipt.merchant = "Macbook";
        assertThat(validator.validate(receipt), empty());
    }

    @Test
    public void testMissingMerchant() {
        CreateReceiptRequest receipt = new CreateReceiptRequest();
        receipt.amount = new BigDecimal(2000.00);
//        validator.validate(receipt);
        assertThat(validator.validate(receipt), hasSize(1));
    }

    // ---- testing ---- //
    @Test
    public void testPostReceipt() {

        CreateReceiptRequest receipt = new CreateReceiptRequest();
        receipt.merchant = "Macbook";
        receipt.amount = new BigDecimal(2000.00);
        assertThat(validator.validate(receipt), empty());
    }
}