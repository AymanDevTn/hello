package edu.cafeteria.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    private Double totalPrice;

    public Cart() {
    }

    public Cart(User user, List<CartItem> items, Double totalPrice) {
		super();
		this.user = user;
		this.items = items;
		this.totalPrice = totalPrice;
	}

	public Cart(User user) {
        this.user = user;
         
		this.totalPrice = (double) 0;
    }

   

    public Cart(String userEmail) {
		 
	}

	 

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addItem(Item item) {
        CartItem cartItem = new CartItem(this, item);
        items.add(cartItem);
      
    }

    public void removeItem(Item item) {
        items.removeIf(cartItem -> cartItem.getItem().equals(item));
        recalculateTotalPrice();
    }

    private void recalculateTotalPrice() {
     
    	totalPrice = items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

	 
}
