package ru.netology.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankData {

   private String login;
   private  String password;
   private String status;

}

