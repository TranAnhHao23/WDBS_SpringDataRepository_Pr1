package cg.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String description;
    private String imageLink;

    // Check xem có chạy được không nhé
    @Transient
    private MultipartFile imageFile;

    @ManyToOne
    @JoinColumn(name = "category_c_id")
    private Category category;

    public Product() {
    }

    public Product(String name, double price, String description, String imageLink, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageLink = imageLink;
        this.category = category;
    }

    public Product(Long id, String name, double price, String description, String imageLink, MultipartFile imageFile, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageLink = imageLink;
        this.imageFile = imageFile;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
