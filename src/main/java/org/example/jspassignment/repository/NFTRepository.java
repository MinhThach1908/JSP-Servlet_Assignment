package org.example.jspassignment.repository;

import org.example.jspassignment.entity.NFT;

import java.util.List;

public interface NFTRepository {
    NFT findById(int id);
    List<NFT> findAll(int limit, int page);
    NFT save(NFT nft);
    NFT update(int id, NFT nft);
    boolean deleteById(int id);
}
