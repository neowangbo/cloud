package com.b5wang.microservice.easymall.easymalltransaction.res;

import com.b5wang.microservice.easymall.easymalltransaction.model.Transaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @RequestMapping(method = RequestMethod.GET)
    public Transaction getTransaction(@RequestParam(value="id", defaultValue="0") Integer id){
        Transaction transaction = new Transaction();
        transaction.setId(100);
        transaction.setFromUserId(99);
        transaction.setToUserId(250);
        transaction.setAmount(2500.90);

        return transaction;
    }

    


}
