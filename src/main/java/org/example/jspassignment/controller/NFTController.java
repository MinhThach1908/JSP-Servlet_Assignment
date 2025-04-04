package org.example.jspassignment.controller;

import org.example.jspassignment.entity.NFT;
import org.example.jspassignment.repository.NFTRepository;
import org.example.jspassignment.repository.MySQLNFTRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class NFTController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(NFTController.class.getName());
    NFTRepository nftRepository = new MySQLNFTRepository();

    // Show form to create a new NFT or edit an existing one
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "list"; // Default action is to list NFTs
        }

        switch (action) {
            case "add":
                showAddForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "list":
                listNFTs(req, resp);
                break;
            case "delete":
                deleteNFT(req, resp);
                break;
            default:
                listNFTs(req, resp);
                break;
        }
    }

    // Handle form submissions (Create, Update NFT)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "add":
                addNFT(req, resp);
                break;
            case "update":
                updateNFT(req, resp);
                break;
            default:
                listNFTs(req, resp);
                break;
        }
    }

    // List NFTs
    private void listNFTs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int limit = 10;
        int page = 1;

        // Get parameters for pagination
        String limitParam = req.getParameter("limit");
        String pageParam = req.getParameter("page");

        if (limitParam != null) {
            limit = Integer.parseInt(limitParam);
        }

        if (pageParam != null) {
            page = Integer.parseInt(pageParam);
        }

        // Fetch NFTs from the database
        List<NFT> nfts = nftRepository.findAll(limit, page);
        req.setAttribute("nfts", nfts);
        req.getRequestDispatcher("/admin/nft/list.jsp").forward(req, resp);
    }

    // Show form to add a new NFT
    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/nft/create-form.jsp").forward(req, resp);
    }

    // Add a new NFT
    private void addNFT(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NFT nft = new NFT();
        nft.setName(req.getParameter("name"));
        nft.setDescription(req.getParameter("description"));
        nft.setPrice(Double.parseDouble(req.getParameter("price")));
        nft.setImageUrl(req.getParameter("imageUrl"));
        nft.setArtistName(req.getParameter("artistName"));
        nft.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));
        nft.setOwnerWalletAddress(req.getParameter("ownerWalletAddress"));
        nft.setStatus(req.getParameter("status"));

        // Validate the NFT data
        if (!nft.isValid()) {
            req.setAttribute("errors", nft.getErrors());
            req.setAttribute("nft", nft);
            req.getRequestDispatcher("/admin/nft/create-form.jsp").forward(req, resp);
        } else {
            nftRepository.save(nft);
            req.setAttribute("msg", "NFT saved");
            resp.sendRedirect("NFTServlet?action=list");
        }
    }

    // Show form to edit an NFT
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int nftId = Integer.parseInt(req.getParameter("nftId"));
        NFT nft = nftRepository.findById(nftId);
        req.setAttribute("nft", nft);
        req.getRequestDispatcher("/admin/nft/edit-form.jsp").forward(req, resp);
    }

    // Update an NFT
    private void updateNFT(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int nftId = Integer.parseInt(req.getParameter("nftId"));
        NFT nft = nftRepository.findById(nftId);

        if (nft == null) {
            req.setAttribute("msg", "NFT not found");
            req.getRequestDispatcher("/admin/nft/list.jsp").forward(req, resp);
        } else {
            nft.setName(req.getParameter("name"));
            nft.setDescription(req.getParameter("description"));
            nft.setPrice(Double.parseDouble(req.getParameter("price")));
            nft.setImageUrl(req.getParameter("imageUrl"));
            nft.setArtistName(req.getParameter("artistName"));
            nft.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));
            nft.setOwnerWalletAddress(req.getParameter("ownerWalletAddress"));
            nft.setStatus(req.getParameter("status"));

            if (!nft.isValid()) {
                req.setAttribute("errors", nft.getErrors());
                req.setAttribute("nft", nft);
                req.getRequestDispatcher("/admin/nft/edit-form.jsp").forward(req, resp);
            } else {
                nftRepository.update(nftId, nft);
                req.setAttribute("msg", "NFT updated successfully");
                resp.sendRedirect("NFTServlet?action=list");
            }
        }
    }

    // Delete an NFT
    private void deleteNFT(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int nftId = Integer.parseInt(req.getParameter("nftId"));
        nftRepository.deleteById(nftId);
        req.setAttribute("msg", "NFT deleted successfully");
        resp.sendRedirect("NFTServlet?action=list");
    }
}
