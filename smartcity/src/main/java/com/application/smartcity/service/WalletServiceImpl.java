package com.application.smartcity.service;

import com.application.smartcity.exception.WalletException;
import com.application.smartcity.model.ServiceType;
import com.application.smartcity.model.Wallet;
import com.application.smartcity.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Override
    public Wallet createWallet(Wallet wallet) throws WalletException {
        if (wallet == null) {
            throw new WalletException("Invalid wallet details!");
        }
        // Additional validations if needed

        return walletRepository.save(wallet);
    }

    @Override
    public Wallet getWalletById(Integer walletId) throws WalletException {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletException("Wallet with ID " + walletId + " not found!"));
    }

    @Override
    public Wallet updateWallet(Integer walletId, Wallet updatedWallet) throws WalletException {
        Wallet existingWallet = getWalletById(walletId);

        // Update wallet properties
        existingWallet.setBalance(updatedWallet.getBalance());
        // Update other properties as needed

        return walletRepository.save(existingWallet);
    }

    @Override
    public void deleteWallet(Integer walletId) throws WalletException {
        Wallet existingWallet = getWalletById(walletId);
        walletRepository.delete(existingWallet);
    }

    @Override
    public List<Wallet> getWalletsByCityService(String cityName, ServiceType serviceType) throws WalletException {
        // Implement logic to retrieve wallets by city service
        // Use walletRepository.findByCityNameAndServiceType(cityName, serviceType) or similar
        return null;
    }

    @Override
    public Wallet topUpWallet(Integer walletId, Double amount) throws WalletException {
        Wallet wallet = getWalletById(walletId);
        if (amount <= 0) {
            throw new WalletException("Invalid top-up amount!");
        }
        wallet.setBalance(wallet.getBalance() + amount);
        return walletRepository.save(wallet);
    }

    @Override
    public Double getWalletBalance(Integer walletId) throws WalletException {
        Wallet wallet = getWalletById(walletId);
        return wallet.getBalance();
    }

    @Override
    public void transferFunds(Integer fromWalletId, Integer toWalletId, Double amount) throws WalletException {
        Wallet fromWallet = getWalletById(fromWalletId);
        Wallet toWallet = getWalletById(toWalletId);

        if (fromWallet.getBalance() < amount) {
            throw new WalletException("Insufficient funds for transfer!");
        }
        fromWallet.setBalance(fromWallet.getBalance() - amount);
        toWallet.setBalance(toWallet.getBalance() + amount);

        walletRepository.save(fromWallet);
        walletRepository.save(toWallet);
    }

    @Override
    public void blockFunds(Integer walletId, Double amount) throws WalletException {
        Wallet wallet = getWalletById(walletId);
        if (wallet.getBalance() < amount) {
            throw new WalletException("Insufficient funds to block!");
        }

        wallet.setBlockedAmount(wallet.getBlockedAmount() + amount);
        wallet.setBalance(wallet.getBalance() - amount);
        walletRepository.save(wallet);
    }

    @Override
    public void unblockFunds(Integer walletId, Double amount) throws WalletException {
        Wallet wallet = getWalletById(walletId);
        if (wallet.getBlockedAmount() < amount) {
            throw new WalletException("Invalid unblock amount!");
        }
        wallet.setBlockedAmount(wallet.getBlockedAmount() - amount);
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);
    }

}
