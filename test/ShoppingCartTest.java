import org.junit.*;

import static org.junit.Assert.*;
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
        assertEquals(80, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SALE, 500));
        assertEquals(73, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SALE, 30));
        assertEquals(71, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SALE, 10));
        assertEquals(70, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SALE, 9));
        assertEquals(70, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SALE, 1));
        assertEquals(0, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.NEW,20));
        assertEquals(0, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.NEW,10));
        assertEquals(0, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.NEW,1));
        assertEquals(80, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 500));
        assertEquals(53, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 30));
        assertEquals(51, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 10));
        assertEquals(50, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 9));
        assertEquals(50, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 2));
        assertEquals(0, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 1));
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
