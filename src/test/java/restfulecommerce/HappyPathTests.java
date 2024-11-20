package restfulecommerce;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import restfulecommerce.testData.OrderData;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import static restfulecommerce.testData.OrderDataBuilder.*;

public class HappyPathTests extends BaseTest{
    private List<OrderData> orderList;

    @BeforeClass
    public void testSetup() {
        this.orderList = new ArrayList<>();
    }

    @Test
    public void testShouldCreateNewOrders() {
        final int totalOrders = 4;

        for (int i = 0; i < totalOrders; i++) {
            this.orderList.add(getNewOrder());
        }

        final APIResponse response = this.request.post("/addOrder", RequestOptions.create()
                .setData(this.orderList));

        final JSONObject responseObject = new JSONObject(response.text());
        final JSONArray ordersArray = responseObject.getJSONArray("orders");

        assertEquals(response.status(), 201);
        assertEquals(responseObject.get("message"), "Orders added successfully!");
        assertNotNull(ordersArray.getJSONObject(0).get("id"));
        assertEquals(this.orderList.get(0).getUserId(), ordersArray.getJSONObject(0).get("user_id"));
        assertEquals(this.orderList.get(0).getProductId(), ordersArray.getJSONObject(0).get("product_id"));
        assertEquals(this.orderList.get(0).getTotalAmt(), ordersArray.getJSONObject(0).get("total_amt"));
    }

    @Test
    public void testShouldGetAllOrders() {
        final APIResponse response = this.request.get("/getAllOrders");

        final JSONObject responseObject = new JSONObject(response.text());
        final JSONArray ordersArray = responseObject.getJSONArray("orders");

        assertEquals(response.status(), 200);
        assertEquals(responseObject.get("message"), "Orders fetched successfully!");
        assertEquals(this.orderList.get(0).getUserId(), ordersArray.getJSONObject(0).get("user_id"));
        assertEquals(this.orderList.get(0).getProductId(), ordersArray.getJSONObject(0).get("product_id"));
        assertEquals(this.orderList.get(0).getTotalAmt(), ordersArray.getJSONObject(0).get("total_amt"));
    }
}
