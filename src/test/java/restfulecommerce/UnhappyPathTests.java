package restfulecommerce;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import restfulecommerce.testData.OrderData;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

import static restfulecommerce.testData.OrderDataBuilder.*;

public class UnhappyPathTests extends BaseTest{
    private List<OrderData> orderList;

    @BeforeClass
    public void testSetup() {
        this.orderList = new ArrayList<>();
    }

    @Test
    public void testShouldNotCreateOrder_WhenProductIdFieldIsMissing() {
        orderList.add(getOrderDataWithMissingProductId());

        final APIResponse response = this.request.post("/addOrder",
                RequestOptions.create().setData(orderList));

        final JSONObject responseObject = new JSONObject(response.text());
        assertEquals(response.status(), 400);
        assertEquals(responseObject.get("message"), "Each order must have user_id, product_id, product_name, product_amount, qty, tax_amt, and total_amt!");
    }
}

