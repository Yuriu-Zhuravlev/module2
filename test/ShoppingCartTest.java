import org.junit.*;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Vycheslav
 */
public class ShoppingCartTest {
    /**
     * Test of appendFormatted method, of class ShoppingCart.
     */
    @Test
    public void testAppendFormatted() {
        StringBuilder sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, "SomeLine", 0, 14);
        assertEquals( "   SomeLine    ",sb.toString());
        sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, "SomeLine", 0, 15);
        assertEquals("   SomeLine     ", sb.toString());
        sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, "SomeLine", 0, 5);
        assertEquals(sb.toString(), "SomeL ");
        sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, "SomeLine", 1, 15);
        assertEquals(sb.toString(), "       SomeLine ");
        sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, "SomeLine", -1, 15);
        assertEquals(sb.toString(), "SomeLine        ");
    }

    /**
     * Test of calculateDiscount method, of class ShoppingCart.
     */
    @Test
    public void testCalculateDiscount() {
        ShoppingCart.Item item = new ShoppingCart.Item();
        item.setType(ShoppingCart.ItemType.SALE);
        item.setQuantity(500);
        assertEquals(80, item.calculateDiscount());
        item.setType(ShoppingCart.ItemType.SALE);
        item.setQuantity(30);
        assertEquals(73, item.calculateDiscount());
        item.setType(ShoppingCart.ItemType.SALE);
        item.setQuantity(10);
        assertEquals(71, item.calculateDiscount());
        item.setType(ShoppingCart.ItemType.SALE);
        item.setQuantity(9);
        assertEquals(70, item.calculateDiscount());
        item.setType(ShoppingCart.ItemType.SALE);
        item.setQuantity(1);
        assertEquals(70, item.calculateDiscount());
        item.setType(ShoppingCart.ItemType.NEW);
        item.setQuantity(20);
        assertEquals(0, item.calculateDiscount());
        item.setType(ShoppingCart.ItemType.NEW);
        item.setQuantity(10);
        assertEquals(0, item.calculateDiscount());
        item.setType(ShoppingCart.ItemType.NEW);
        item.setQuantity(1);
        assertEquals(0, item.calculateDiscount());
        item.setType(ShoppingCart.ItemType.SECOND_FREE);
        item.setQuantity(500);
        assertEquals(80, item.calculateDiscount());
        item.setType(ShoppingCart.ItemType.SECOND_FREE);
        item.setQuantity(30);
        assertEquals(53, item.calculateDiscount());
        item.setType(ShoppingCart.ItemType.SECOND_FREE);
        item.setQuantity(10);
        assertEquals(51, item.calculateDiscount());
        item.setType(ShoppingCart.ItemType.SECOND_FREE);
        item.setQuantity(9);
        assertEquals(50, item.calculateDiscount());
        item.setType(ShoppingCart.ItemType.SECOND_FREE);
        item.setQuantity(2);
        assertEquals(50, item.calculateDiscount());
        item.setType(ShoppingCart.ItemType.SECOND_FREE);
        item.setQuantity(1);
        assertEquals(0, item.calculateDiscount());
    }

    @Test
    public void testFormatTicket(){
        ShoppingCart cart = new ShoppingCart();
        assertEquals("No items.",cart.formatTicket());
        cart.addItem("Apple", 0.99, 5, ShoppingCart.ItemType.NEW);
        assertEquals("# Item  Price Quan. Discount Total \n" +
                "----------------------------------\n" +
                "1 Apple  $.99     5        - $4.95 \n" +
                "----------------------------------\n" +
                "1                            $4.95 ",cart.formatTicket());
        cart.addItem("Banana", 20.00, 4, ShoppingCart.ItemType.SECOND_FREE);
        assertEquals("# Item    Price Quan. Discount  Total \n" +
                "-------------------------------------\n" +
                "1 Apple    $.99     5        -  $4.95 \n" +
                "2 Banana $20.00     4      50% $40.00 \n" +
                "-------------------------------------\n" +
                "2                              $44.95 ",cart.formatTicket());
        cart.addItem("A long piece of toilet paper", 17.20, 1, ShoppingCart.ItemType.SALE);
        assertEquals("# Item                          Price Quan. Discount  Total \n" +
                "-----------------------------------------------------------\n" +
                "1 Apple                          $.99     5        -  $4.95 \n" +
                "2 Banana                       $20.00     4      50% $40.00 \n" +
                "3 A long piece of toilet paper $17.20     1      70%  $5.16 \n" +
                "-----------------------------------------------------------\n" +
                "3                                                    $50.11 ",cart.formatTicket());
        cart.addItem("Nails", 2.00, 500, ShoppingCart.ItemType.REGULAR);
        assertEquals("# Item                          Price Quan. Discount   Total \n" +
                "------------------------------------------------------------\n" +
                "1 Apple                          $.99     5        -   $4.95 \n" +
                "2 Banana                       $20.00     4      50%  $40.00 \n" +
                "3 A long piece of toilet paper $17.20     1      70%   $5.16 \n" +
                "4 Nails                         $2.00   500      50% $500.00 \n" +
                "------------------------------------------------------------\n" +
                "4                                                    $550.11 ",cart.formatTicket());
    }
}
