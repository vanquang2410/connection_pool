package com.openlearnhub.connectionpool.controller;


import com.openlearnhub.connectionpool.entity.AccountBank;
import com.openlearnhub.connectionpool.service.impl.AccountBankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/account")
public class AccountBankController {
    @Autowired
    private AccountBankServiceImpl accountBankService;

    @GetMapping("/{id}")
    public AccountBank getInfo(@PathVariable Long id){
       return accountBankService.getInfo(id);
    }

}
