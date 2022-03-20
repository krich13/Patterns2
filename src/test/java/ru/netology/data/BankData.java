package ru.netology.data;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class BankData {

    private String login;
    private String password;
    private String status;

}

