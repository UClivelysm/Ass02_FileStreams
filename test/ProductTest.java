import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;
    private String formattedString;

    @BeforeEach
    void setUp() {
        // Create a consistent Product instance for each test.
        product = new Product("123456", "Test Product", "This is a test product.", 1234.56);
        // Obtain the formatted string (125 characters long per the toString() specification)
        formattedString = product.toString();
    }

    @Test
    void testToStringFormat() {
        // The toString() output should be exactly 125 characters:
        // 35 for name, 75 for description, 6 for ID, 9 for cost.
        final int expectedLength = 35 + 75 + 6 + 9;
        String result = product.toString();
        assertEquals(expectedLength, result.length(), "Formatted string should be exactly 125 characters long.");

        // Check each section with its proper padding:
        String expectedName = String.format("%-35s", product.getName());
        String expectedDescription = String.format("%-75s", product.getDescription());
        String expectedID = String.format("%-6s", product.getID());
        String expectedCost = String.format("%9.2f", product.getCost());
        String expectedCombined = expectedName + expectedDescription + expectedID + expectedCost;
        assertEquals(expectedCombined, result, "The formatted output from toString() does not match expected formatting.");
    }

    @Test
    void testToFormattedString() {
        String formatted = product.toFormattedString();
        String expected = String.format("Product name: %s%nProduct ID: %s%nDescription: %s%nCost: $%.2f",
                product.getName(), product.getID(), product.getDescription(), product.getCost());
        assertEquals(expected, formatted, "toFormattedString() output does not match the expected format.");
    }

    @Test
    void testToProductValid() {
        // Convert the valid formatted string back to a Product.
        Product productFromString = Product.toProduct(formattedString);
        assertEquals(product, productFromString, "Product converted from string should be equal to the original product.");
    }

    @Test
    void testToProductInvalid() {
        // Provide an invalid string that is too short.
        String invalidString = "Too short string";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Product.toProduct(invalidString);
        });
        assertTrue(exception.getMessage().contains("Input string is not in the expected format."),
                "The exception message should indicate that the input is not in the expected format.");
    }

    @Test
    void testCSVJSONXMLMethods() {
        // Test the toCSV() method.
        String csv = product.toCSV();
        assertTrue(csv.contains(product.getID()), "CSV output should contain the product ID.");
        assertTrue(csv.contains(product.getName()), "CSV output should contain the product name.");
        assertTrue(csv.contains(product.getDescription()), "CSV output should contain the product description.");
        assertTrue(csv.contains(String.valueOf(product.getCost())), "CSV output should contain the product cost.");

        // Test the toJSON() method.
        String json = product.toJSON();
        assertTrue(json.contains("\"ID\":\"" + product.getID() + "\""), "JSON output should contain the product ID.");
        assertTrue(json.contains("\"name\":\"" + product.getName() + "\""), "JSON output should contain the product name.");

        // Test the toXML() method.
        String xml = product.toXML();
        assertTrue(xml.contains("<ID>" + product.getID() + "</ID>"), "XML output should contain the product ID.");
        assertTrue(xml.contains("<name>" + product.getName() + "</name>"), "XML output should contain the product name.");
    }

    @Test
    void testEqualsAndHashCode() {
        // Create another product with the same values.
        Product sameProduct = new Product("123456", "Test Product", "This is a test product.", 1234.56);
        assertEquals(product, sameProduct, "Two products with the same data should be equal.");
        assertEquals(product.hashCode(), sameProduct.hashCode(), "Hash codes should be equal for equal products.");

        // Create a product with different data.
        Product differentProduct = new Product("654321", "Different Product", "Different description", 6543.21);
        assertNotEquals(product, differentProduct, "Products with different data should not be equal.");
    }
}
