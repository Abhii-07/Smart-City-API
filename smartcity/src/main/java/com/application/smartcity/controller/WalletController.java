package com.application.smartcity.controller;

import com.application.smartcity.exception.WalletException;
import com.application.smartcity.model.Wallet;
import com.application.smartcity.model.ServiceType;
import com.application.smartcity.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/{walletId}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable Integer walletId) {
        try {
            Wallet wallet = walletService.getWalletById(walletId);
            return ResponseEntity.ok(wallet);
        } catch (WalletException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{walletId}")
    public ResponseEntity<Wallet> updateWallet(@PathVariable Integer walletId, @RequestBody Wallet updatedWallet) {
        try {
            Wallet wallet = walletService.updateWallet(walletId, updatedWallet);
            return ResponseEntity.ok(wallet);
        } catch (WalletException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{walletId}")
    public ResponseEntity<String> deleteWallet(@PathVariable Integer walletId) {
        try {
            walletService.deleteWallet(walletId);
            return ResponseEntity.ok("Wallet with ID " + walletId + " deleted successfully");
        } catch (WalletException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/city/{cityName}/service/{serviceType}")
    public ResponseEntity<List<Wallet>> getWalletsByCityService(@PathVariable String cityName, @PathVariable ServiceType serviceType) {
        try {
            List<Wallet> wallets = walletService.getWalletsByCityService(cityName, serviceType);
            return ResponseEntity.ok(wallets);
        } catch (WalletException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{walletId}/topup/{amount}")
    public ResponseEntity<Wallet> topUpWallet(@PathVariable Integer walletId, @PathVariable Double amount) {
        try {
            Wallet wallet = walletService.topUpWallet(walletId, amount);
            return ResponseEntity.ok(wallet);
        } catch (WalletException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{walletId}/balance")
    public ResponseEntity<Double> getWalletBalance(@PathVariable Integer walletId) {
        try {
            Double balance = walletService.getWalletBalance(walletId);
            return ResponseEntity.ok(balance);
        } catch (WalletException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/transfer/{fromWalletId}/{toWalletId}/{amount}")
    public ResponseEntity<String> transferFunds(@PathVariable Integer fromWalletId, @PathVariable Integer toWalletId, @PathVariable Double amount) {
        try {
            walletService.transferFunds(fromWalletId, toWalletId, amount);
            return ResponseEntity.ok("Funds transferred successfully");
        } catch (WalletException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{walletId}/block/{amount}")
    public ResponseEntity<String> blockFunds(@PathVariable Integer walletId, @PathVariable Double amount) {
        try {
            walletService.blockFunds(walletId, amount);
            return ResponseEntity.ok("Funds blocked successfully");
        } catch (WalletException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{walletId}/unblock/{amount}")
    public ResponseEntity<String> unblockFunds(@PathVariable Integer walletId, @PathVariable Double amount) {
        try {
            walletService.unblockFunds(walletId, amount);
            return ResponseEntity.ok("Funds unblocked successfully");
        } catch (WalletException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
