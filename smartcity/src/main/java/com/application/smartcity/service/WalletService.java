package com.application.smartcity.service;

import com.application.smartcity.exception.WalletException;
import com.application.smartcity.model.ServiceType;
import com.application.smartcity.model.Wallet;

import java.util.List;

public interface WalletService {
    Wallet createWallet(Wallet wallet) throws WalletException;
    Wallet getWalletById(Integer walletId) throws WalletException;
    Wallet updateWallet(Integer walletId, Wallet updatedWallet) throws WalletException;
    void deleteWallet(Integer walletId) throws WalletException;

    List<Wallet> getWalletsByCityService(String cityName, ServiceType serviceType) throws WalletException;

    Wallet topUpWallet(Integer walletId, Double amount) throws WalletException;
    Double getWalletBalance(Integer walletId) throws WalletException;

    void transferFunds(Integer fromWalletId, Integer toWalletId, Double amount) throws WalletException;
    void blockFunds(Integer walletId, Double amount) throws WalletException;
    void unblockFunds(Integer walletId, Double amount) throws WalletException;
}
