package com.project.clothing_store.models;

import javax.persistence.*;

@Entity
public class Clothes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Lob
    @Column
    private String coverLink, itemName;
    private int price, typeId;
//    private boolean isRead;

//    public String getShopLink() {
//        return shopLink;
//    }
//
//    public void setShopLink(String shopLink) {
//        this.shopLink = shopLink;
//    }

    private String shopLink;

    public Clothes() {
    }

    public Clothes(String itemName, String coverLink, int price, int typeId) {
        this.coverLink = coverLink;
        this.itemName = itemName;
        this.price = price;
        this.typeId = typeId;

//        this.isRead = isRead;
//        this.shopLink = shopLink;
    }

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "favorite_books",
//            joinColumns = @JoinColumn(name = "favbook_id"),
//            inverseJoinColumns = @JoinColumn(name = "subscriber_id")
//    )
//    private Set<User> subscribers = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getCoverLink() {
        return coverLink;
    }

    public void setCoverLink(String coverLink) {
        this.coverLink = coverLink;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


//    public boolean isRead() {
//        return isRead;
//    }
//
//    public void setRead(boolean read) {
//        isRead = read;
//    }

//    public Set<User> getSubscribers() {
//        return subscribers;
//    }
//
//    public void setSubscribers(Set<User> subscribers) {
//        this.subscribers = subscribers;
//    }
}
