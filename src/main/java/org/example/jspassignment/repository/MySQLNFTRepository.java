package org.example.jspassignment.repository;

import org.example.jspassignment.entity.NFT;
import org.example.jspassignment.helper.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MySQLNFTRepository implements NFTRepository {

    private static final String TABLE_NAME = "nfts";

    @Override
    public NFT findById(int nftId) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE nft_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, nftId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapNFT(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error while finding NFT by id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<NFT> findAll(int limit, int page) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE status = 'for sale' LIMIT ? OFFSET ?";
        List<NFT> nfts = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, limit);
            stmt.setInt(2, (page - 1) * limit);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                nfts.add(mapNFT(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching NFT list: " + e.getMessage());
        }
        return nfts.isEmpty() ? Collections.emptyList() : nfts;
    }

    @Override
    public NFT save(NFT nft) {
        String query = "INSERT INTO " + TABLE_NAME + " (name, description, image_url, price, artist_name, category_id, owner_wallet_address, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, nft.getName());
            stmt.setString(2, nft.getDescription());
            stmt.setString(3, nft.getImageUrl());
            stmt.setDouble(4, nft.getPrice());
            stmt.setString(5, nft.getArtistName());
            stmt.setInt(6, nft.getCategoryId());
            stmt.setString(7, nft.getOwnerWalletAddress());
            stmt.setString(8, nft.getStatus());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        nft.setNftId(generatedKeys.getInt(1));  // Set generated ID
                        return nft;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while saving NFT: " + e.getMessage());
        }
        return null;
    }

    @Override
    public NFT update(int nftId, NFT nft) {
        String query = "UPDATE " + TABLE_NAME + " SET name = ?, description = ?, image_url = ?, price = ?, artist_name = ?, category_id = ?, owner_wallet_address = ?, status = ? WHERE nft_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nft.getName());
            stmt.setString(2, nft.getDescription());
            stmt.setString(3, nft.getImageUrl());
            stmt.setDouble(4, nft.getPrice());
            stmt.setString(5, nft.getArtistName());
            stmt.setInt(6, nft.getCategoryId());
            stmt.setString(7, nft.getOwnerWalletAddress());
            stmt.setString(8, nft.getStatus());
            stmt.setInt(9, nftId);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                nft.setNftId(nftId);  // Retain the original ID
                return nft;
            }
        } catch (SQLException e) {
            System.out.println("Error while updating NFT: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteById(int nftId) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE nft_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, nftId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Error while deleting NFT: " + e.getMessage());
        }
        return false;
    }

    private NFT mapNFT(ResultSet rs) throws SQLException {
        NFT nft = new NFT();
        nft.setNftId(rs.getInt("nft_id"));
        nft.setName(rs.getString("name"));
        nft.setDescription(rs.getString("description"));
        nft.setImageUrl(rs.getString("image_url"));
        nft.setPrice(rs.getDouble("price"));
        nft.setCreationDate(rs.getTimestamp("creation_date"));
        nft.setArtistName(rs.getString("artist_name"));
        nft.setCategoryId(rs.getInt("category_id"));
        nft.setOwnerWalletAddress(rs.getString("owner_wallet_address"));
        nft.setStatus(rs.getString("status"));

        return nft;
    }
}
