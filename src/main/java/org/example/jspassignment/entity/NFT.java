package org.example.jspassignment.entity;

import java.security.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NFT {
    private int nftId;
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private Map<String, String> errors;

    public NFT() {
        this.nftId = nftId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.creationDate = creationDate;
        this.artistName = artistName;
        this.categoryId = categoryId;
        this.ownerWalletAddress = ownerWalletAddress;
        this.status = status;
    }

    public int getNftId() {
        return nftId;
    }

    public void setNftId(int nftId) {
        this.nftId = nftId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getOwnerWalletAddress() {
        return ownerWalletAddress;
    }

    public void setOwnerWalletAddress(String ownerWalletAddress) {
        this.ownerWalletAddress = ownerWalletAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private Date creationDate;
    private String artistName;
    private int categoryId;
    private String ownerWalletAddress;
    private String status;

    public boolean isValid() {
        return errors.isEmpty();
    }

    public Map<String, String> getErrors() {
        if (errors == null) {
            errors = new HashMap<>();
        }
        return errors;
    }
}
