import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GownRentalShop extends JFrame {

    private JTextField searchField;
    private JTextArea resultArea;
    private JComboBox<String> sizeComboBox;
    private JComboBox<String> colorComboBox;
    private List<Gown> gowns;
    private List<CartItem> cart;
    private String username;

    public GownRentalShop(String username) {
        this.username = username;

        // Set up the frame
        setTitle("Gown Rental Shop");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.ORANGE);

        // Initialize gown list and cart
        gowns = new ArrayList<>();
        cart = new ArrayList<>();
        
        // Adding different types of gowns
        gowns.add(new Gown("Carnival Gown", 4327, new String[]{"Small", "Medium", "Large", "Extra Large"}));
        gowns.add(new Gown("Gallas Gown", 6721, new String[]{"Medium", "Large"}));
        gowns.add(new Gown("Prom Gown", 7577, new String[]{"Small", "Medium"}));
        gowns.add(new Gown("Homecoming Gown", 5950, new String[]{"Small", "Medium", "Large", "Extra Large"}));
        gowns.add(new Gown("Fashion Show Gown", 8733, new String[]{"Medium", "Large", "Extra Large"}));
        gowns.add(new Gown("Funeral Gown", 7394, new String[]{"Small", "Medium"}));
        gowns.add(new Gown("Party Gown", 8192, new String[]{"Medium", "Large"}));
        
        // New gowns added
        gowns.add(new Gown("Military Ball Gown", 1234, new String[]{"Small", "Medium", "Large"}));
        gowns.add(new Gown("Museum Gala Gown", 5678, new String[]{"Medium", "Large"}));
        gowns.add(new Gown("Art Gallery Opening Gown", 9101, new String[]{"Small", "Medium"}));
        gowns.add(new Gown("Sweet 18 Party Gown", 9876, new String[]{"Small", "Medium", "Large"}));

        // Search field
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Color.ORANGE);
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchGowns();
            }
        });
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Size selection combo box
        sizeComboBox = new JComboBox<>();
        searchPanel.add(new JLabel("Select Size:"));
        searchPanel.add(sizeComboBox);

        // Color selection combo box
        colorComboBox = new JComboBox<>();
        colorComboBox.addItem("Labrador Blue");
        colorComboBox.addItem("Amber");
        colorComboBox.addItem("Liseran Purple");
        colorComboBox.addItem("Amaranth");
        colorComboBox.addItem("Falu Red");
        colorComboBox.addItem("Lime");
        colorComboBox.addItem("Sap Green");
        searchPanel.add(new JLabel("Select Color:"));
        searchPanel.add(colorComboBox);

        // Log Out button
        JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main().setVisible(true);
                dispose(); // Close the GownRentalShop window
            }
        });
        searchPanel.add(logoutButton);

        add(searchPanel, BorderLayout.NORTH);

        // Result area
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.ORANGE);

        // See prices button
        JButton seePricesButton = new JButton("See Prices");
        seePricesButton.setFont(new Font("Helvetica", Font.PLAIN, 12));
        seePricesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPrices();
            }
        });
        buttonPanel.add(seePricesButton);

        // Add to cart button
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setFont(new Font("Helvetica", Font.PLAIN, 12));
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToCart();
            }
        });
        buttonPanel.add(addToCartButton);

        // Cancel from cart button
        JButton cancelFromCartButton = new JButton("Cancel from Cart");
        cancelFromCartButton.setFont(new Font("Helvetica", Font.PLAIN, 12));
        cancelFromCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelFromCart();
            }
        });
        buttonPanel.add(cancelFromCartButton);

        // Purchase button
        JButton purchaseButton = new JButton("Purchase");
        purchaseButton.setFont(new Font("Helvetica", Font.PLAIN, 12));
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completePurchase();
            }
        });
        buttonPanel.add(purchaseButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void searchGowns() {
        String searchText = searchField.getText().toLowerCase();
        StringBuilder results = new StringBuilder();
        for (Gown gown : gowns) {
            if (gown.getName().toLowerCase().contains(searchText)) {
                results.append(gown.getName()).append("\n");

                // Update size combo box with the sizes of the found gown
                sizeComboBox.removeAllItems();
                for (String size : gown.getSizes()) {
                    sizeComboBox.addItem(size);
                }

                // Set the color combo box to the available color of the gown
                colorComboBox.setSelectedItem(gown.getColor());
            }
        }
        resultArea.setText(results.toString());
    }

    private void displayPrices() {
        StringBuilder prices = new StringBuilder();
        for (Gown gown : gowns) {
            prices.append(gown.getName())
                  .append(": ₱")
                  .append(gown.getPrice())
                  .append(" (Sizes: ");
            for (String size : gown.getSizes()) {
                prices.append(size).append(" ");
            }
            prices.append(") (Color: ")
                  .append(gown.getColor())
                  .append(")\n");
        }
        resultArea.setText(prices.toString());
    }

    private void addToCart() {
        String selectedGown = searchField.getText().toLowerCase();
        String selectedSize = (String) sizeComboBox.getSelectedItem();
        String selectedColor = (String) colorComboBox.getSelectedItem();
        for (Gown gown : gowns) {
            if (gown.getName().toLowerCase().equals(selectedGown)) {
                if (selectedSize == null || selectedColor == null) {
                    resultArea.setText("Please select size and color.");
                    return;
                }
                gown.setColor(selectedColor); // Set the chosen color for the gown
                cart.add(new CartItem(gown, selectedSize));
                resultArea.setText(gown.getName() + " (" + selectedSize + ", " + selectedColor + ") has been added to your cart.");
                return;
            }
        }
        resultArea.setText("Gown not found. Please search and select a valid gown.");
    }

    private void cancelFromCart() {
        String selectedGown = searchField.getText().toLowerCase();
        String selectedSize = (String) sizeComboBox.getSelectedItem();
        for (CartItem item : cart) {
            if (item.getGown().getName().toLowerCase().equals(selectedGown) && item.getSize().equals(selectedSize)) {
                cart.remove(item);
                resultArea.setText(item.getGown().getName() + " (" + selectedSize + ") has been removed from your cart.");
                return;
            }
        }
        resultArea.setText("Gown not found in your cart. Please search and select a valid gown.");
    }

    private void completePurchase() {
        if (username == null || username.isEmpty()) {
            resultArea.setText("We cannot process your order because you have no login username and password.");
            return;
        }

        if (cart.isEmpty()) {
            resultArea.setText("Your cart is empty. Add items to cart before purchasing.");
            return;
        }

        StringBuilder receipt = new StringBuilder();
        receipt.append("Thank you for your purchase!\nHave a memorable occasion.\n\n");
        receipt.append("Your Receipt:\n");

        int total = 0;
        for (CartItem item : cart) {
            receipt.append(item.getGown().getName())
                   .append(": ₱")
                   .append(item.getGown().getPrice())
                   .append(" (Size: ")
                   .append(item.getSize())
                   .append(")")
                   .append(" (Color: ")
                   .append(item.getGown().getColor())
                   .append(")\n");
            total += item.getGown().getPrice();
        }
        receipt.append("\nTotal: ₱").append(total).append("\n");

        // Generate 9-digit random ticket number
        int ticketNumber = new Random().nextInt(111119000) + 111111000;
        receipt.append("\nYour Ticket Number: ");
        receipt.append("*").append(ticketNumber).append("*");

        resultArea.setText(receipt.toString());
        cart.clear(); // Clear the cart after purchase
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GownRentalShop("Guest").setVisible(true);
            }
        });
    }

    // Inner class to represent a Gown
    class Gown {
        private String name;
        private int price;
        private String[] sizes;
        private String color;

        public Gown(String name, int price, String[] sizes) {
            this.name = name;
            this.price = price;
            this.sizes = sizes;
            this.color = ""; // Default color is empty
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public String[] getSizes() {
            return sizes;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    // Inner class to represent an item in the shopping cart
    class CartItem {
        private Gown gown;
        private String size;

        public CartItem(Gown gown, String size) {
            this.gown = gown;
            this.size = size;
        }

        public Gown getGown() {
            return gown;
        }

        public String getSize() {
            return size;
        }
    }
}

